package com.udacity.jwdnd.course1.cloudstorage.models;

import lombok.Data;

/**
 * Created by Brian Smith on 8/8/21.
 * Description:
 */
@Data
public class UploadedFile {

    private Integer fileId;
    private String fileName;
    private String contentType;
    private Long fileSize;
    private Integer userId;
    private byte[] fileData;
}
