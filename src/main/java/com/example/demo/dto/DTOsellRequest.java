package com.example.demo.dto;

public class DTOsellRequest {
    
    private Long id;
    private int balance;
    private Long vkId;

    public Long getVkId() {
        return vkId;
    }
    public void setVkId(Long vkId) {
        this.vkId = vkId;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
}