package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Brian Smith on 8/4/21.
 * Description: Controller to handle user login requests
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping()
    public String loginView() {
        return "login";
    }
}
