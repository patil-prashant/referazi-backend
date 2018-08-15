package com.referazi.dao;

import com.referazi.models.Transaction;

import java.util.List;

public interface TransactionDao {

    List<Transaction> getTransactionsOfUser(Integer userId);

    Integer getPointsOfUser(Integer userId);

    void insertTransaction(Transaction transaction);
}
