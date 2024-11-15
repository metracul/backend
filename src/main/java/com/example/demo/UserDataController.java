package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserDataController {

    @Autowired
    private UserDataService userDataService;
    private Map<String, Object> createUserDataResponse(UserData userData) {
        Map<String, Object> response = new HashMap<>();
        
        response.put("balance", userData.getBalance());
        response.put("capybaras", userData.getCapybaras());
        response.put("unlocked_element", new ArrayList<>()); // Это может быть пустой массив или содержать данные
        response.put("env", new HashMap<>()); // Заполните согласно структуре `env`
        response.put("shop", new ArrayList<>()); // Заполните согласно структуре `shop`
        response.put("love_lake", new HashMap<>()); // Заполните данными `love_lake`
        response.put("devil_room", new HashMap<>()); // Заполните данными `devil_room`
    
        return response;
    }
    @GetMapping("/initialize")
    public ResponseEntity<Map<String, Object>> initializeUser(@RequestParam Long userId) {
    UserData userData = userDataService.loadUserData(userId);

    if (userData == null) {
        // Создаем пользователя с дефолтными значениями
        userData = new UserData();
        userData.setVkId(userId);
        userData.setBalance(0);
        userData.setCapybaras(new Integer[]{}); // Пустой массив капибар
        userData.setLakeId(0); // Дефолтное значение, можно заменить на нужное
        userData.setDevilId(0); // Дефолтное значение, можно заменить на нужное
        userData.setBackground(1); // Дефолтное значение, можно заменить на нужное
        userData.setLakeStatus(0); // Дефолтное значение
        userData.setLakeTicksLeft(LocalDateTime.now()); // Можно задать необходимое значение
        
        userDataService.saveUserData(userData);
    }
    
    Map<String, Object> response = createUserDataResponse(userData);

    return ResponseEntity.ok(response);
}

    @PostMapping("/create")
    public ResponseEntity<UserData> createUser(@RequestBody UserData userData) {
    // Проверяем, что vk_id не пустой, и задаем его вручную, если это необходимо
    if (userData.getVkId() == null) {
        throw new RuntimeException("vk_id должен быть задан вручную.");
    }

    UserData savedUser = userDataService.saveUserData(userData);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
}

    @PostMapping("/{userId}/addCapybara")
    public UserData addCapybaraToUser(@PathVariable Long userId, @RequestParam Long capybaraId) {
        return userDataService.addCapybaraToUser(userId, capybaraId);
    }

    @PostMapping("/updateBalance")
    public ResponseEntity<String> updateBalance(@RequestBody Map<String, Object> request) {
        Long vkId = ((Number) request.get("vkId")).longValue();
        int balance = (int) request.get("balance");

        userDataService.updateUserBalance(vkId, balance);

        return ResponseEntity.ok("Balance updated successfully");
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