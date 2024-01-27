package com.spring6.spring6webapp.controllers;

import com.spring6.spring6webapp.services.BookService;
import com.spring6.spring6webapp.services.BookServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @RequestMapping("/books")
    public String getBooks(Model model){
        model.addAttribute("books", this.bookService.findAll());
        return "Books";
    }
}
