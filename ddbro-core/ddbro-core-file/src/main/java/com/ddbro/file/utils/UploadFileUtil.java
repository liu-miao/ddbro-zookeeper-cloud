package com.ddbro.file.utils;


import lombok.extern.slf4j.Slf4j;


/**
 * 文件工具
 *
 * @author liumiao
 * @date 2022/10/07
 */
@Slf4j
public class UploadFileUtil {

//
//    /**
//     * 上传文件
//     * updateFile" + "/" + filePath + "/" + LocalDate.now();  是长传文件的保存位置
//     *
//     * @param file    文件
//     * @param mainDir 主目录
//     * @return {@link String}
//     */
//    public static String tempUploadFile(MultipartFile file, String mainDir) {
//        Assert.isFalse(null == file, "文件不能为空");
//        Assert.isFalse(CharSequenceUtil.isBlank(mainDir), "文件路径不能为空");
//        String filename = file.getOriginalFilename();
//        Assert.isFalse(CharSequenceUtil.isBlank(filename) || filename.length() > 50, "文件名称异常请检查");
//        // @@ 相隔  方便 下載的時候取名字
//        String filePath = generateSaveFilePath(mainDir);
//        String name = filePath + "/" + filename;
//        Assert.isFalse(file.getSize() <= 0, "文件不能是空文件");
//
//        File dir = new File(filePath);
//        if (!dir.exists()) {
//            FileUtil.mkdir(dir);
//        }
//        File toFile = FileUtil.newFile(name);
//        try (OutputStream os = new FileOutputStream(toFile);
//             InputStream ins = file.getInputStream()) {
//            int bytesRead = 0;
//            byte[] buffer = new byte[2048];
//            while ((bytesRead = ins.read(buffer, 0, 2048)) != -1) {
//                os.write(buffer, 0, bytesRead);
//            }
//        } catch (IOException e) {
//            log.error(ExceptionUtil.stacktraceToString(e));
//            Assert.isError("文件上传失败，请联系管理员！");
//
//        }
//
//        return name;
//    }
//
//
//    /**
//     * 生成保存文件路径
//     *
//     * @param mainDir 上传到服务的主目录
//     * @return {@link String}
//     */
//    public static String generateSaveFilePath(String mainDir) {
//        return LoadPathConstant.UPDATE_FILE + "/" + mainDir +
//                "/" + LocalDate.now() + "/" + IdUtil.objectId() +
//                "/" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
//    }


}
