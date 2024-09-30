package cn.ss.controller;

import cn.ss.pojo.Result;
import cn.ss.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/files")
public class FilesController {
    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile file) {
        log.info("name: {}, age: {}, file: {}", name, age, file);
        String filename = file.getOriginalFilename();
        filename= UUID.randomUUID().toString().replace("-","")+ filename.substring(filename.lastIndexOf("."));
        try {
            file.transferTo(new File("D:/" +filename ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Result.success();
    }


    @PostMapping("/uploadoss")
    public Result uploadoss(String name, Integer age, MultipartFile file) throws IOException {
        log.info("name: {}, age: {}, file: {}", name, age, file.getOriginalFilename());

        String url = aliOSSUtils.upload(file);

        log.info("url: {}", url);
        return Result.success(url);
    }

}
