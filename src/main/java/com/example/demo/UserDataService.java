package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserDataService {

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private CapybaraRepository capybaraRepository;

    public UserData addCapybaraToUser(Long userId, Long capybaraId) {
        UserData user = userDataRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        Capybara capybara = capybaraRepository.findById(capybaraId)
                .orElseThrow(() -> new RuntimeException("Капибара не найдена"));

        Integer[] currentCapybaras = user.getCapybaras();
        Integer[] newCapybaras = Arrays.copyOf(currentCapybaras, currentCapybaras.length + 1);
        newCapybaras[newCapybaras.length - 1] = capybara.getId().intValue();
        user.setCapybaras(newCapybaras);

        return userDataRepository.save(user);
    }

    public UserData updateUserBalance(Long userId, int clicks) {
        UserData user = userDataRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        
        int capybaraCount = user.getCapybaras() != null ? user.getCapybaras().length : 0;
        user.setBalance(clicks);

        return userDataRepository.save(user);
    }
    public Integer loadUserBalance(Long userId) {
        return userDataRepository.findUserBalanceById(userId);
    }
    public UserData saveUserData(UserData userData) {
        return userDataRepository.save(userData);
    }

    public UserData loadUserData(Long userId) {
        return userDataRepository.findById(userId).orElse(null);
    }

    
}