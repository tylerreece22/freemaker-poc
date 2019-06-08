package com.freemarker.poc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class TemplateController {

    @GetMapping("/template")
    public String init(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", "Tyler");
        return "template";
    }
}
