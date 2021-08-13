package com.udacity.jwdnd.course1.cloudstorage.models;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Brian Smith on 8/12/21.
 * Description:
 */
@Data
@Builder
public class Credential {

    private Integer credentialId;
    private String url;
    private String username;
    private String password;
    private String key;
    private Integer userId;
}
