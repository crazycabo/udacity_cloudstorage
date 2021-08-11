package com.udacity.jwdnd.course1.cloudstorage.datasource;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("Select * from NOTES where userid = #{userId} and noteTitle = #{noteTitle}")
    Note getNoteByName(String noteTitle, int userId);

    @Select("Select * from NOTES where userid = #{userId}")
    List<Note> getNotesByUserId(int userid);

    @Insert("Insert into NOTES (noteTile, noteDescription, userid) " +
            "VALUES (#{noteTile}, #{noteDescription}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int updateNote(Note note);

    @Delete("Delete from NOTE where noteId = #{noteId}")
    int deleteNoteById(int noteId);
}
