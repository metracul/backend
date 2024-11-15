package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


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
    public UserData deleteCapybaraToUser(Long userId, Long capybaraId) {
        UserData user = userDataRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        Capybara capybara = capybaraRepository.findById(capybaraId)
                .orElseThrow(() -> new RuntimeException("Капибара не найдена"));
        
    Integer[] currentCapybaras = user.getCapybaras();
    if (currentCapybaras == null || currentCapybaras.length == 0) {
        throw new RuntimeException("У пользователя нет капибар для удаления.");
    }

    // Преобразуем Integer[] в List<Long> для корректного сравнения
    List<Long> updatedCapybarasList = Arrays.stream(currentCapybaras)
                                             .map(Integer::longValue) // Преобразование Integer в Long
                                             .collect(Collectors.toList());

    // Проверяем наличие и удаляем
    if (!updatedCapybarasList.contains(capybara.getId())) {
        throw new RuntimeException("Капибара с таким ID не найдена у пользователя.");
    }

    updatedCapybarasList.remove(capybara.getId());

    // Преобразуем List<Long> обратно в Integer[]
    Integer[] updatedCapybaras = updatedCapybarasList.stream()
                                                     .map(Long::intValue) // Преобразование Long обратно в Integer
                                                     .toArray(Integer[]::new);
    
    user.setCapybaras(updatedCapybaras);
    return userDataRepository.save(user);
}
    //     if (currentCapybaras == null || currentCapybaras.length == 0) {
    //         throw new RuntimeException("У пользователя нет капибар для удаления.");
    //     }
    //     List<Integer> updatedCapybarasList = new ArrayList<>(Arrays.asList(currentCapybaras));
    //     updatedCapybarasList.remove(capybara.getId());
    //     Integer[] updatedCapybaras = updatedCapybarasList.toArray(new Integer[0]);
    //     user.setCapybaras(updatedCapybaras);
    //     return userDataRepository.save(user);
    // }

    public UserData updateUserBalance(Long userId, int clicks) {
        UserData user = userDataRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        
        // int capybaraCount = user.getCapybaras() != null ? user.getCapybaras().length : 0;
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