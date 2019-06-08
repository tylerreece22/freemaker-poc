package com.freemarker.poc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class ViewController {

    @GetMapping("/template")
    public String init(Model model) {
        model.addAttribute("userName", "user9999");
        model.addAttribute("userId", "U9999");
        return "template";
    }
}
