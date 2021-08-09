package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.dtos.FileDTO;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by Brian Smith on 8/8/21.
 * Description:
 */
@Controller()
@RequestMapping("/files")
public class FileController {

    private final FileService fileUploadService;
    private final UserService userService;

    public FileController(FileService fileUploadService, UserService userService) {
        this.fileUploadService = fileUploadService;
        this.userService = userService;
    }

    @ModelAttribute("fileDTO")
    public FileDTO getFileDTO() {
        return new FileDTO();
    }

    @GetMapping("/{fileName}")
    public Object getFileContent(@PathVariable("fileName") String fileName, Authentication authentication) {
        User user = this.userService.getUser(authentication.getName());

        File file = this.fileUploadService.getFileByName(fileName, user.getUserId());

        return (file != null) ? ResponseEntity.status(200)
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment;filename=\"" + fileName + "\"")
                .body(file.getFileData()) : null;
    }

    @PostMapping
    public String uploadFile(Authentication authentication, Model model, @ModelAttribute("fileDTO") MultipartFile file) {
        User user = this.userService.getUser(authentication.getName());

        if (file.isEmpty()) {
            model.addAttribute("updateFail", "File should not be empty.");
            return "result";
        }

        if (this.fileUploadService.fileNameExists(file.getOriginalFilename(), user.getUserId())) {
            model.addAttribute("updateFail", "File name already exists.");
            return "result";
        }

        if (file.getSize() > 1024 * 500) {
            model.addAttribute("updateFail", "File size exceed limit of 500Kb");
            return "result";
        }

        try {
            int fileId = this.fileUploadService.uploadFile(file, user.getUserId());
            model.addAttribute("updateSuccess", fileId);
        } catch (IOException e) {
            model.addAttribute("updateFail", e.getLocalizedMessage());
        }

        return "result";
    }

    @GetMapping("/delete/{fileId}")
    public String deleteFile(@PathVariable("fileId") int fileId, Model model) {

        int deletedFileId = this.fileUploadService.deleteFile(fileId);

        if (deletedFileId > 0) {
            model.addAttribute("updateSuccess", deletedFileId);
        } else {
            model.addAttribute("updateFail", "Unknown error.");
        }

        return "result";
    }
}
