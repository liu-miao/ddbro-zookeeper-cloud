package com.ddbro.core.exception.handler;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.ddbro.core.constant.Constants;
import com.ddbro.core.constant.ResultCodeEnum;
import com.ddbro.core.exception.BaseException;
import com.ddbro.core.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.CompletionException;

/**
 * 全局异常处理器
 *
 * @author miaol
 * @date 2020-04-11 10:14
 */
@Slf4j
@RestControllerAdvice
@ConditionalOnWebApplication
public class GlobalExceptionHandler {

    /**
     * 处理自定义异常
     *
     * @param e 异常
     */
    @ExceptionHandler({BaseException.class})
    @ResponseBody
    public R<Void> handleException(BaseException e) {
        String msg = e.getMessage();
        if (CharSequenceUtil.isBlank(msg)) {
            msg = ResultCodeEnum.SERVER_ERROR.getMessage();
        }
        return R.failed(Constants.GLOBAL_ERROR_CODE, msg);
    }


    /**
     * 处理非法参数异常
     *
     * @param e e
     * @return {@link R}<{@link ?}>
     */
    @ExceptionHandler({
            IllegalArgumentException.class
    })
    @ResponseBody
    public R<Void> handleException(IllegalArgumentException e) {
        log.error(ExceptionUtil.stacktraceToString(e));
        String message = ResultCodeEnum.SERVER_ERROR.getMessage();
        return R.failed(Constants.GLOBAL_ERROR_CODE, message);
    }

    /**
     * 参数绑定异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public R<Void> handleException(BindException e) {
        return wrapperBindingResult(e.getBindingResult());
    }

    /**
     * 参数校验(Valid)异常，将校验失败的所有异常组合成一条错误信息
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public R<Void> handleException(MethodArgumentNotValidException e) {
        return wrapperBindingResult(e.getBindingResult());
    }

    /**
     * 参数校验(Valid)异常，将校验失败的所有异常组合成一条错误信息
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = {
            CompletionException.class
    })
    @ResponseBody
    public R<Void> handleException(CompletionException e) {
        String msg = e.getCause().getMessage();
        String[] split = msg.split(":");
        return R.failed(Constants.GLOBAL_ERROR_CODE, split[split.length - 1]);
    }


    /**
     * 包装绑定异常结果
     *
     * @param bindingResult 绑定结果
     * @return 异常结果
     */
    private R<Void> wrapperBindingResult(BindingResult bindingResult) {
        StringBuffer msg = new StringBuffer();
        for (ObjectError error : bindingResult.getAllErrors()) {
            if (CharSequenceUtil.isNotBlank(msg.toString())) {
                msg.append("，");
            }
            msg.append(error.getDefaultMessage() == null ? "" : error.getDefaultMessage());
        }

        return R.failed(Constants.GLOBAL_ERROR_CODE, msg.toString());
    }


    @ExceptionHandler(BadCredentialsException.class)
    public R<Void> handleException(BadCredentialsException e) {
        log.debug(ExceptionUtil.stacktraceToString(e));
        String message = ResultCodeEnum.USER_OR_PASS_ERROR.getMessage();
        return R.failed(HttpStatus.UNAUTHORIZED.value(), message);
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    public R<Void> handleException(InsufficientAuthenticationException e) {
        log.debug(ExceptionUtil.stacktraceToString(e));
        String message = ResultCodeEnum.UNAUTHORIZED.getMessage();
        return R.failed(HttpStatus.UNAUTHORIZED.value(), message);
    }

    @ExceptionHandler(AuthenticationException.class)
    public R<Void> handleException(AuthenticationException e) {
        log.debug(ExceptionUtil.stacktraceToString(e));
        String message = "认证或授权失败,请联系管理员";
        return R.failed(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public R<Void> handleException(AccessDeniedException e) {
        log.debug(ExceptionUtil.stacktraceToString(e));
        return R.failed(Constants.GLOBAL_ERROR_CODE, ResultCodeEnum.NO_OPERATOR_AUTH.getMessage());
    }

    /**
     * 用户不存在
     *
     * @param e e
     * @return {@link R}<{@link Void}>
     */
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public R<Void> handleException(InternalAuthenticationServiceException e) {
        return R.failed(Constants.GLOBAL_ERROR_CODE, ResultCodeEnum.USER_OR_PASS_ERROR.getMessage());
    }


    /**
     * 处理自定义异常
     *
     * @param e 异常
     */
    @ExceptionHandler(Exception.class)
    public R<Void> handleException(Exception e) {
        log.error(ExceptionUtil.stacktraceToString(e));
        String message = ResultCodeEnum.SERVER_ERROR.getMessage();
        return R.failed(Constants.GLOBAL_ERROR_CODE, message);
    }
}
