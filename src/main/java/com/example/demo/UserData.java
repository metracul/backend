package com.example.demo;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_data")
public class UserData {

    @Id
    private Long userId; // userId передается от клиента, а не генерируется автоматически

    private int balance; // Баланс пользователя
    private int capybaraCount; // Количество капибар у пользователя
    private LocalDateTime lastLoginTime; // Время последнего входа пользователя

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private List<Capybara> capybaraList = new ArrayList<>(); // Список капибар у пользователя

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

    public List<Capybara> getCapybaraList() {
        return capybaraList;
    }

    public void setCapybaraList(List<Capybara> capybaraList) {
        this.capybaraList = capybaraList;
    }
}
