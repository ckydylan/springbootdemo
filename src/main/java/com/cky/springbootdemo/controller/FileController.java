package com.cky.springbootdemo.controller;

import com.cky.springbootdemo.common.ExceptionType;
import com.cky.springbootdemo.exception.CustomException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
public class FileController {
    @PostMapping("/upload")
    public ResponseEntity<String> upload(MultipartFile file, HttpServletRequest request) {
        String format = LocalDate.now().format(DateTimeFormatter.ofPattern("/yyyy/MM/dd/"));
        String realPath = request.getServletContext().getRealPath("/img") + format;
        File folder = new File(realPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String oldName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));

        try {
            file.transferTo(new File(folder, newName));
            String url = request.getScheme() + "://" + request.getServerName() + ":"
                    + request.getServerPort() + "/img" + format + newName;
            return ResponseEntity.ok(url);
        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomException(ExceptionType.OTHER_ERROR.getCode(), e.getMessage());
        }
    }

    //    @PostMapping("/uploads")
//    public String upload(MultipartFile[] files, HttpServletRequest request) {
//        String format = LocalDate.now().format(DateTimeFormatter.ofPattern("/yyyy/MM/dd/"));
//        String realPath = request.getServletContext().getRealPath("/img") + format;
//        File folder = new File(realPath);
//        if (!folder.exists()) {
//            folder.mkdirs();
//        }
//        for (MultipartFile file : files) {
//            String oldName = file.getOriginalFilename();
//            String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
//
//            try {
//                file.transferTo(new File(folder, newName));
//                String url = request.getScheme() + "://" + request.getServerName() + ":"
//                        + request.getServerPort() + "/img" + format + newName;
//                System.out.println(url);
//            } catch (IOException e) {
//                e.printStackTrace();
//                throw new CustomException(ExceptionType.OTHER_ERROR.getCode(), e.getMessage());
//            }
//        }
//        return "success";
//    }
//
    @Value("${file.download.root.dir}")
    private String dirPath;

    @GetMapping("/download/{filename}")
    public ResponseEntity<FileSystemResource> fileDownload(@PathVariable String filename,
                                                           HttpServletRequest request) {
        File file = new File(dirPath, filename);
//        if (!file.exists()) {
//            throw new CustomException(ExceptionType.NOT_FOUND_ERROR.getCode(),
//                    ExceptionType.NOT_FOUND_ERROR.getMsg());
//        }
//        try {
//            filename = getFilename(request, filename);
//
//            // 设置响应头
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentDispositionFormData("attachment", filename);
//            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//            return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new CustomException(ExceptionType.OTHER_ERROR.getCode(), e.getMessage());
//        }

        if (file.exists()) {
            try {
                filename = getFilename(request, filename);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache,bo-store,must-revalidate");
                headers.setContentDispositionFormData("attachment", filename);
                return ResponseEntity.ok().headers(headers).contentLength(file.length())
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body(new FileSystemResource(file));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    private String getFilename(HttpServletRequest request, String filename) throws Exception {
        // IE不同版本User-Agent中出现的关键词
        String[] ieBrowserKeyWords = {"MSIE", "Trident", "Edge"};
        // 获取请求头代理信息
        String userAgent = request.getHeader("User-Agent");
        for (String keyWord : ieBrowserKeyWords) {
            if (userAgent.contains(keyWord)) {
                // IE内核浏览器，统一为UTF-8编码显示，并对转换的+进行更正
                return URLEncoder.encode(filename, "UTF-8").replace("+", " ");
            }
        }
        // 其它浏览器统一为ISO-8859-1编码显示
        return new String(filename.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
    }
}
