package com.udacity.jwdnd.course1.cloudstorage.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Note {

    private Integer noteId;
    private String title;
    private String description;
    private Integer userId;
}
