package seo.dale.spring.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/fileupload")
public class FileUploadController {

    private static Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String uploadForm() {
        return "fileupload";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String upload(@RequestPart("file") MultipartFile file, MemberForm memberForm) throws IOException {
        logger.info("- name : {}", file.getName());
        logger.info("- original file name : {}", file.getOriginalFilename());
        logger.info("- content type : {}", file.getContentType());
        logger.info("- size : {}", file.getSize());
        logger.info("- bytes : {}", new String(file.getBytes()));
        file.transferTo(new File("D:/temp/" + file.getOriginalFilename()));
        return "redirect:/fileupload";
    }

}
