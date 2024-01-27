package com.newproject.NewProjectExercise.controllers;

import com.newproject.NewProjectExercise.services.EnvService;
import com.newproject.NewProjectExercise.services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EnvController {

    private final EnvService envService;

    public EnvController(EnvService envService){
        this.envService = envService;
    }

    public String getEnv(){
        return this.envService.getEnv() + "Enviornment";
    }
}
