package com.udacity.jwdnd.course1.cloudstorage.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Brian Smith on 8/8/21.
 * Description:
 */
@Data
public class FileDTO {

    public MultipartFile file;
}
