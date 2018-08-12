package com.referazi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Conversation {
    private Integer id;
    private Integer user1Id;
    private User user1;
    private Integer user2Id;
    private User user2;
    private List<History> messages;
    private Boolean textModeOn;
    private Date createdAt;
    private Date updatedAt;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(Integer user1Id) {
        this.user1Id = user1Id;
    }

    public Integer getUser2Id() {
        return user2Id;
    }

    public void setUser2Id(Integer user2Id) {
        this.user2Id = user2Id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public List<History> getMessages() {
        return messages;
    }

    public void setMessages(List<History> messages) {
        this.messages = messages;
    }

    public Boolean getTextModeOn() {
        return textModeOn;
    }

    public void setTextModeOn(Boolean textModeOn) {
        this.textModeOn = textModeOn;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "id=" + id +
                ", user1Id=" + user1Id +
                ", user1=" + user1 +
                ", user2Id=" + user2Id +
                ", user2=" + user2 +
                ", messages=" + messages +
                ", textModeOn=" + textModeOn +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
