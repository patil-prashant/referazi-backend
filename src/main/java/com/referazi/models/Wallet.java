package com.referazi.models;

import java.util.List;

public class Wallet {

    private User user;

    private Integer balance;

    List<Transaction> transactions;

    public Wallet(User user, Integer balance, List<Transaction> transactions) {
        this.user = user;
        this.balance = balance;
        this.transactions = transactions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "user=" + user +
                ", balance=" + balance +
                ", transactions=" + transactions +
                '}';
    }
}
