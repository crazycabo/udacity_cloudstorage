package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.datasource.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Brian Smith on 8/12/21.
 * Description:
 */
@Service
public class CredentialService {

    private final CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    public Credential getCredentialById(int credentialId) {
        return this.credentialMapper.getCredentialById(credentialId);
    }

    public List<Credential> getAllCredentials(int userId) {
        return this.credentialMapper.getCredentialByUserId(userId);
    }

    public int createCredential(Credential credential) {
        if (credential.getCredentialId() == null) {
            return this.credentialMapper.createCredentialById(credential);
        } else {
            return this.credentialMapper.updateCredentialById(credential);
        }
    }

    public int deleteCredential(int credentialId) {
        return this.credentialMapper.deleteCredentialById(credentialId);
    }
}
