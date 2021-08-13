package com.udacity.jwdnd.course1.cloudstorage.dtos;

import lombok.Data;

@Data
public class NoteDTO {

    public Integer noteId;
    public String title;
    public String description;
}
