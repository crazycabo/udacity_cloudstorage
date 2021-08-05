package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Brian Smith on 8/4/21.
 * Description:
 */
@Controller()
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String getHomePage(Model model) {
        return "home";
    }
}
