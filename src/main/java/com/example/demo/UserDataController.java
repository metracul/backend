package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserDataController {

    @Autowired
    private UserDataRepository userDataRepository;

    @PostMapping("/save")
    public UserData saveUserData(@RequestBody UserData userData) {
        return userDataRepository.save(userData);
    }

    @GetMapping("/load")
    public UserData loadUserData(@RequestParam Long userId) {
        return userDataRepository.findById(userId).orElse(null);
    }
}