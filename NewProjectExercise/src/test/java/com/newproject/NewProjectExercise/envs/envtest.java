package com.newproject.NewProjectExercise.envs;

import com.newproject.NewProjectExercise.controllers.EnvController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("QA")
@SpringBootTest
public class envtest {

    @Autowired
    EnvController envController;

    @Test
    void sayHello(){
        System.out.println(envController.getEnv());;
    }

}
