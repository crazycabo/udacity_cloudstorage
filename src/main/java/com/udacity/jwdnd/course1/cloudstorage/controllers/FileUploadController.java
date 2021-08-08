package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.services.FileUploadService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Brian Smith on 8/8/21.
 * Description:
 */
@Controller()
@RequestMapping("/files")
public class FileUploadController {

    private FileUploadService fileUploadService;
    private UserService userService;

    public FileUploadController(FileUploadService fileUploadService, UserService userService) {
        this.fileUploadService = fileUploadService;
        this.userService = userService;
    }

    @PostMapping
    public String uploadFile(
            Authentication authentication,
            Model model,
            MultipartFile multipartFile) {


        return "";
    }
}
