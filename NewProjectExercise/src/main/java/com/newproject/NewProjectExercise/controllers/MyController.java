package com.newproject.NewProjectExercise.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class MyController {
    public String hellow(){
        System.out.println("Hello world");
        return "hello";
    }
}
