package com.jump.zhu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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


    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) {
        File file = new File("E:\\download\\chrome\\picture\\old\\80845930_p0.jpg");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition","attachment;fileName=1.jpg");
        FileInputStream in = null;
        ServletOutputStream out = null;
        try {
            in = new FileInputStream(file);
            out = response.getOutputStream();
            byte[] b = new byte[1024];
            int length = 0;
            while ((length = in.read(b)) > 0){
                out.write(b,0,length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }finally {
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out!=null){
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
