package com.ddbro.core.constant;

/**
 * iresponse枚举
 * 异常返回码枚举接口
 *
 * @author liumiao
 * @date 2021/08/04
 */
public interface IResponseEnum {

    /**
     * 获取返回码
     *
     * @return 返回码
     */
    int getCode();

    /**
     * 获取返回信息
     *
     * @return 返回信息
     */
    String getMessage();

}
