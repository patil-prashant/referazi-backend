package com.referazi.dao;

import com.referazi.models.Conversation;
import org.apache.ibatis.annotations.Param;

public interface ConversationDao {

    void createConversation(Conversation conversation);

    Conversation getConversation(@Param("user1Id") Integer user1Id, @Param("user2Id") Integer user2Id);

    Integer getConversationId(@Param("user1Id") Integer user1Id, @Param("user2Id") Integer user2Id);

    void updateTextMode(Conversation conversation);
}
