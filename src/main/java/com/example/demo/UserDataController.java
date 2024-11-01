package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserDataController {

    @Autowired
    private UserDataService userDataService;

    @PostMapping("/updateBalance")
    public UserData updateBalance(@RequestParam Long userId, @RequestParam int clicks) {
        return userDataService.updateUserBalance(userId, clicks);
    }

    @PostMapping("/save")
    public UserData saveUserData(@RequestBody UserData userData) {
        return userDataService.saveUserData(userData);
    }

    @GetMapping("/load")
    public UserData loadUserData(@RequestParam Long userId) {
        return userDataService.loadUserData(userId);
    }
}
