package com.example.Assignment;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssignmentController {
    @RequestMapping("/")
    public String HelloTeam(){
        return "Hello Backend Team";
    }
}
