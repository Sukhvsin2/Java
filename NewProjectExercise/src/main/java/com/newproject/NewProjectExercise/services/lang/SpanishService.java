package com.newproject.NewProjectExercise.services.lang;

import com.newproject.NewProjectExercise.services.GreetingService;
import org.springframework.stereotype.Service;

@Service
public class SpanishService implements GreetingService {
    @Override
    public String sayHello() {
        return "Spanish Service!";
    }
}
