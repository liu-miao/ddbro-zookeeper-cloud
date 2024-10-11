package com.ddbro.file.controller;

import com.ddbro.file.utils.DownloadFileUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("file")
public class DownloadController {
    @CrossOrigin // 跨域允许所有
    @GetMapping(value = "download")
    public void download(@RequestParam("filePath") String filePath, HttpServletResponse response) {
        DownloadFileUtil.downloadLocal(filePath, response);
    }
}
