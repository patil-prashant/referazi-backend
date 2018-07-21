package com.referazi.dao;

import com.referazi.models.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageDao {

    void insertMessage(Message message);

    List<Message> getMessages();
}
