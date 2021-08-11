package com.udacity.jwdnd.course1.cloudstorage.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Note {

    private Integer noteId;
    private String noteTitle;
    private String noteDescription;
    private Integer userId;
}
