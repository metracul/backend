package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserDataController {

    @Autowired
    private UserDataService userDataService;

    // Обновление баланса
    @PostMapping("/updateBalance")
    public UserData updateBalance(@RequestBody Map<String, Object> payload) {
        Long userId = ((Number) payload.get("userId")).longValue();
        int clicks = (int) payload.get("clicks");
        return userDataService.updateUserBalance(userId, clicks);
    }

    // Сохранение данных пользователя
    @PostMapping("/save")
    public UserData saveUserData(@RequestBody UserData userData) {
        return userDataService.saveUserData(userData);
    }

    // Загрузка данных пользователя
    @GetMapping("/load")
    public UserData loadUserData(@RequestBody Map<String, Object> payload) {
        Long userId = ((Number) payload.get("userId")).longValue();
        return userDataService.loadUserData(userId);
    }
}
