package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.dtos.CredentialDTO;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    private final UserService userService;
    private final EncryptionService encryptionService;
    private final CredentialService credentialService;

    public CredentialController(
            UserService userService,
            EncryptionService encryptionService,
            CredentialService credentialService) {
        this.userService = userService;
        this.encryptionService = encryptionService;
        this.credentialService = credentialService;
    }

    @ModelAttribute("credentialDTO")
    public CredentialDTO getCredentialDTO() {
        return new CredentialDTO();
    }

    @GetMapping("/{credentialId}")
    public String getCredential(@PathVariable("credentialId") int credentialId, Model model, Authentication authentication) {
        try {
            Credential credential = this.credentialService.getCredentialById(credentialId);
            String encryptedPassword = this.encryptionService.decryptValue(credential.getPassword(), credential.getKey());

            model.addAttribute("updateSuccess", true);
        } catch(Exception e) {
            model.addAttribute("updateFail", "Encrypted password could not be retrieved.");
        }

        return "result";
    }

    @PostMapping("/create")
    public String createCredential(@ModelAttribute("credentialDTO") CredentialDTO credDTO, Model model, Authentication authentication) {
        User user = this.userService.getUser(authentication.getName());

        String encryptionKey = this.encryptionService.createEncryptionKey();
        String encryptedPassword = this.encryptionService.encryptValue(credDTO.getPassword(), encryptionKey);

        Credential credential = Credential.builder()
                .credentialId(credDTO.getCredentialId())
                .username(credDTO.getUsername())
                .password(encryptedPassword)
                .key(encryptionKey)
                .url(credDTO.getUrl())
                .userId(user.getUserId())
                .build();

        int credentialId = this.credentialService.createCredential(credential);

        if (credentialId > 0) {
            model.addAttribute("updateSuccess", true);
        } else {
            model.addAttribute("updateFail", "Error creating credential.");
        }

        return "result";
    }

    @GetMapping("/delete/{credentialId}")
    public String deleteCredential(@PathVariable("credentialId") int credId, Model model, Authentication authentication) {
        User user = this.userService.getUser(authentication.getName());

        int deletedCredentialId = this.credentialService.deleteCredential(credId, user.getUserId());

        if (deletedCredentialId > 0) {
            model.addAttribute("updateSuccess", true);
        } else {
            model.addAttribute("updateFail", "Credential could not be deleted");
        }

        return "result";
    }
}
