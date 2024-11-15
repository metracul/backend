package com.example.demo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_data")
public class UserData {

    @Id
    @Column(name = "vk_id")
    private Long vkId;

    @Column(name = "capybaras", columnDefinition = "integer[]")
    private Integer[] capybaras;

    @Column(name = "lake_id")
    private int lakeId;

    @Column(name = "devil_id")
    private int devilId;

    @Column(name = "background")
    private int background;

    @Column(name = "lake_status")
    private int lakeStatus;

    @Column(name = "balance")
    private int balance;

    @Column(name = "lake_ticks_left")
    private LocalDateTime lakeTicksLeft;

    // Getters and Setters

    public Long getVkId() {
        return vkId;
    }

    public void setVkId(Long vkId) {
        this.vkId = vkId;
    }

    public Integer[] getCapybaras() {
        return capybaras;
    }

    public void setCapybaras(Integer[] capybaras) {
        this.capybaras = capybaras;
    }

    public int getLakeId() {
        return lakeId;
    }

    public void setLakeId(int lakeId) {
        this.lakeId = lakeId;
    }

    public int getDevilId() {
        return devilId;
    }

    public void setDevilId(int devilId) {
        this.devilId = devilId;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public int getLakeStatus() {
        return lakeStatus;
    }

    public void setLakeStatus(int lakeStatus) {
        this.lakeStatus = lakeStatus;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public LocalDateTime getLakeTicksLeft() {
        return lakeTicksLeft;
    }

    public void setLakeTicksLeft(LocalDateTime lakeTicksLeft) {
        this.lakeTicksLeft = lakeTicksLeft;
    }
    
}