package com.newproject.NewProjectExercise.services.envs;

import com.newproject.NewProjectExercise.services.EnvService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("UAT")
@Service
public class UatEnv implements EnvService {
    @Override
    public String getEnv() {
        return "UAT-";
    }
}
