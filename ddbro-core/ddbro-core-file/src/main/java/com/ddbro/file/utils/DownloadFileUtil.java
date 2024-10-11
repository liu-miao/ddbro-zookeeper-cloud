package com.ddbro.file.utils;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.ddbro.core.constant.ResultCodeEnum;
import com.ddbro.core.exception.Assert;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 下载文件工具
 *
 * @author liumiao
 * @date 2022/10/07
 */
@Slf4j
public class DownloadFileUtil {
    private DownloadFileUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void downloadLocal(String url, HttpServletResponse response) {

        File file = new File(url);
        Assert.isTrue(file.exists(), "文件不存在");
        String name = file.getName();

        Path path = Paths.get(url);

        // 获取路径的第一级
        String firstLevel = path.getName(0).toString();
        Assert.isTrue("plugin".equals(firstLevel) || "data".equals(firstLevel), "输入地址有误");

        response.reset();
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        response.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*");
        response.setHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS, "*");
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        try (FileInputStream fileInputStream = new FileInputStream(file);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
             ServletOutputStream responseOutputStream = response.getOutputStream();
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(responseOutputStream)) {

            String fileName = URLEncoder.encode(name, StandardCharsets.UTF_8);
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=" + fileName);
            byte[] buffer = new byte[1024];
            int readLen;
            while ((readLen = bufferedInputStream.read(buffer)) != -1) {
                bufferedOutputStream.write(buffer, 0, readLen);
            }
        } catch (Exception e) {
            log.error(ExceptionUtil.stacktraceToString(e));
            ResultCodeEnum.DOWNLOAD_ERROR.assertFail();
        }

    }
}
