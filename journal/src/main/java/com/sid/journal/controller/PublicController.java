package com.sid.journal.controller;

import com.sid.journal.entity.User;
import com.sid.journal.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserEntryService userService;

    @GetMapping("/health-check")
    public String healthcheck (){
        return "ok";
    }

    @PostMapping("create-user")
    public void createUser (@RequestBody User user){
        userService.saveNewUser(user);
    }

}
