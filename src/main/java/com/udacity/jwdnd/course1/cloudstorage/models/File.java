package com.udacity.jwdnd.course1.cloudstorage.models;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Brian Smith on 8/8/21.
 * Description:
 */
@Data
@Builder
public class File {

    private Integer fileId;
    private String fileName;
    private String contentType;
    private Long fileSize;
    private Integer userId;
    private byte[] fileData;
}
