package com.referazi.dao;

import com.referazi.models.Message;
import com.referazi.models.ProfileType;

import java.util.List;

public interface QueryAnswerDao {

    void insertQuery(Message message);

    List<Message> getQueries(ProfileType profileType);

    List<Message> getAnswers(Integer queryId);
}
