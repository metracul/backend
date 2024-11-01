package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserDataService {

    @Autowired
    private UserDataRepository userDataRepository;

    // Метод обновления баланса
    public UserData updateUserBalance(Long userId, int clicks) {
        Optional<UserData> optionalUser = userDataRepository.findById(userId);
        UserData user;

        if (optionalUser.isPresent()) {
            // Если пользователь существует, обновляем его
            user = optionalUser.get();
            updateUserBalanceAndTime(user, clicks);
        } else {
            // Если пользователя нет, создаем новую запись
            user = new UserData();
            user.setUserId(userId);  // Устанавливаем userId, пришедший от клиента
            user.setBalance(0);       // Начальный баланс
            user.setCapybaraCount(1); // Начальное количество капибар
            user.setLastLoginTime(LocalDateTime.now());
        }

        return userDataRepository.save(user);
    }

    // Метод сохранения данных пользователя
    public UserData saveUserData(UserData userData) {
        return userDataRepository.save(userData);
    }

    // Метод загрузки данных пользователя по userId
    public UserData loadUserData(Long userId) {
        return userDataRepository.findById(userId).orElse(null);
    }

    private void updateUserBalanceAndTime(UserData user, int clicks) {
        LocalDateTime lastLogin = user.getLastLoginTime();
        LocalDateTime now = LocalDateTime.now();

        long hours = Duration.between(lastLogin, now).toHours();
        int coefficient = calculateCoefficient(user.getCapybaraCount());
        int earnedAmount = (int) (hours * coefficient + clicks * coefficient);

        user.setBalance(user.getBalance() + earnedAmount);
        user.setLastLoginTime(now);
    }

    private int calculateCoefficient(int capybaraCount) {
        return 10 * capybaraCount;  // Коэффициент зависит от количества капибар
    }
}
