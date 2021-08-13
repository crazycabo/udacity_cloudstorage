package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.dtos.NoteDTO;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;
    private final UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @ModelAttribute("noteDTO")
    public NoteDTO getNoteDTO() {
        return new NoteDTO();
    }

    @GetMapping("/{noteTitle}")
    public Note getNoteContent(@PathVariable("noteTitle") String noteTitle, Authentication authentication) {
        User user = this.userService.getUser(authentication.getName());

        return this.noteService.getNoteByTitle(noteTitle, user.getUserId());
    }

    @PostMapping("/create")
    public String uploadNote(@ModelAttribute("noteDTO") NoteDTO noteDTO, Authentication authentication, Model model) {
        User user = this.userService.getUser(authentication.getName());

        Note note = Note.builder()
                .noteId(noteDTO.getNoteId())
                .title(noteDTO.getTitle())
                .description(noteDTO.getDescription())
                .userId(user.getUserId())
                .build();

        int noteId = this.noteService.uploadNote(note);
        model.addAttribute("updateSuccess", noteId);

        if (noteId < 0) {
            model.addAttribute("updateFail", "Note could not be saved!");
        }

        return "result";
    }

    @GetMapping("/delete/{noteId}")
    public String deleteNote(@PathVariable("noteId") int noteId, Model model) {

        int deleteNoteId = this.noteService.deleteNote(noteId);

        if (deleteNoteId > 0) {
            model.addAttribute("updateSuccess", deleteNoteId);
        } else {
            model.addAttribute("updateFail", "Unknown error. Note could not be deleted.");
        }

        return "result";
    }
}
