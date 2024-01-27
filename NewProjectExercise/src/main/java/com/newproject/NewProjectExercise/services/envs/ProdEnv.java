package com.newproject.NewProjectExercise.services.envs;

import com.newproject.NewProjectExercise.services.EnvService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("Prod")
@Service
public class ProdEnv implements EnvService {
    @Override
    public String getEnv() {
        return "Prod-";
    }
}
