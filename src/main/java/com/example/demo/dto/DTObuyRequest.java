package com.example.demo.dto;

import com.example.demo.Proposal;

public class DTObuyRequest {
    
    private Long id;
    private Proposal proposal;
    private int balance;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Proposal getProposal() {
        return proposal;
    }
    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
}
