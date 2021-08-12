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

    public int uploadNote(Note note) {
        if (note.getNoteId() != null) {
            return this.noteMapper.updateNote(note);
        } else {
            return this.noteMapper.saveNote(note);
        }
    }

    public int deleteNote(int noteId) {
        return this.noteMapper.deleteNoteById(noteId);
    }
}
