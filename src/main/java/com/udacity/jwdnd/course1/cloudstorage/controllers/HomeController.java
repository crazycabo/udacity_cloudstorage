package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.dtos.FileDTO;
import com.udacity.jwdnd.course1.cloudstorage.dtos.NoteDTO;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Brian Smith on 8/4/21.
 * Description:
 */
@Controller()
@RequestMapping("/home")
public class HomeController {

    private final UserService userService;
    private final FileService fileService;
    private final NoteService noteService;

    public HomeController(UserService userService, FileService fileService, NoteService noteService) {
        this.userService = userService;
        this.fileService = fileService;
        this.noteService = noteService;
    }

    @ModelAttribute("fileDTO")
    public FileDTO getFileDTO() {
        return new FileDTO();
    }

    @ModelAttribute("noteDTO")
    public NoteDTO getNoteDTO() {
        return new NoteDTO();
    }

    @GetMapping
    public String getHomePage(Authentication authentication, Model model) {
        User user  = this.userService.getUser(authentication.getName());
        model.addAttribute("files", this.fileService.getAllFiles(user.getUserId()));
        model.addAttribute("notes", this.noteService.getAllNotes(user.getUserId()));

        return "home";
    }
}
