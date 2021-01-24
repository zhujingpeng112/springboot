package com.jump.zhu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class FileController {
    @RequestMapping("/upload")
    public String upload(MultipartFile upload,String username) throws IOException {
        System.out.println("username :  "+username);
        upload.transferTo(new File("D:/",upload.getOriginalFilename()));
        System.out.println("fileName :  "+upload.getOriginalFilename());
        return "success";
    }
}
