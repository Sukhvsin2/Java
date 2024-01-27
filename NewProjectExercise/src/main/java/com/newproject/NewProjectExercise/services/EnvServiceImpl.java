package com.newproject.NewProjectExercise.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("parent")
@Service
public class EnvServiceImpl implements EnvService{
    @Override
    public String getEnv() {
        return "Env Impl";
    }
}
