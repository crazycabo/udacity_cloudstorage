package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.datasource.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public List<Note> getAllNotes(int userId) {
        return noteMapper.getNotesByUserId(userId);
    }

    public Note getNoteByTitle(String title, int userId) {
        return noteMapper.getNoteByName(title, userId);
    }

    public int uploadNote(String title, String description, int userId) {
        return this.noteMapper.updateNote(
                Note.builder()
                        .noteTitle(title)
                        .noteDescription(description)
                        .userId(userId)
                        .build()
        );
    }

    public int deleteNote(int noteId) {
        return this.noteMapper.deleteNoteById(noteId);
    }

    public boolean noteExists(String title, int userId) {
        return this.noteMapper.getNoteByName(title, userId) != null;
    }
}
