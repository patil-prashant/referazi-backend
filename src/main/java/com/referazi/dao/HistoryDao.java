package com.referazi.dao;

import com.referazi.models.History;

import java.util.List;

public interface HistoryDao {

    void insertMessageHistory(History history);

    List<History> getMessageHistory(Integer conversationId);

    History getHistoryMessageById(Integer id);

    void markUnReadAsRead(Integer conversationId);
}
