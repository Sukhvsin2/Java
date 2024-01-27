package com.spring6.spring6webapp.controllers;

import com.spring6.spring6webapp.domain.Author;
import com.spring6.spring6webapp.services.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthorController {
    private AuthorService authorService;
    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    @RequestMapping("/authors")
    public String findAll(Model model){
        model.addAttribute("authors", this.authorService.findAll());
        return "Authors";
    }
}
