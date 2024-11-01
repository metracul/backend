package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_data")
public class UserData {

    @Id
    private Long userId; // Теперь не генерируем, а принимаем с клиента

    private int balance;
    private int capybaraCount;
    private LocalDateTime lastLoginTime;

    // Геттеры и сеттеры

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getCapybaraCount() {
        return capybaraCount;
    }

    public void setCapybaraCount(int capybaraCount) {
        this.capybaraCount = capybaraCount;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
