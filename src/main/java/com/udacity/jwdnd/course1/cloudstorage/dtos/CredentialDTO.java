package com.udacity.jwdnd.course1.cloudstorage.dtos;

import lombok.Data;

/**
 * Created by Brian Smith on 8/12/21.
 * Description:
 */
@Data
public class CredentialDTO {

    private Integer credentialId;
    private String url;
    private String username;
    private String password;
    private String key;
}
