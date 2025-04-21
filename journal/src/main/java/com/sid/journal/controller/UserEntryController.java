package com.sid.journal.controller;

import com.sid.journal.api.response.WeatherResponse;
import com.sid.journal.service.WeatherService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sid.journal.entity.User;
import com.sid.journal.service.UserEntryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserEntryController {
    @Autowired
    private UserEntryService userService;

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public ResponseEntity<?> greeting() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        WeatherResponse weather = weatherService.getWeather("Mumbai");
        System.out.println(weather);
        String greeting = "";
        if (weather != null) {
            greeting = ", Today's weather feels like "+ weather.getCurrent().getFeelsLike();
        }
        return new ResponseEntity<>("Hi " + username + greeting, HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userService.findByUserName(userName);
        System.out.println("Received user data: " + userInDb);
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());
        userService.saveNewUser(userInDb);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
