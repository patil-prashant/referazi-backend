package com.referazi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction {

    private Integer id;

    private Integer userId;

    private User user;

    private Integer thirdPartyId;

    private User thirdParty;

    private TransactionType type;

    private Integer points;

    private Integer actionId;

    private Action action;

    private Date createdAt;

    public Transaction(){

    }

    public Transaction(Integer userId, Integer thirdPartyId, TransactionType type, Integer actionId) {
        this.userId = userId;
        this.thirdPartyId = thirdPartyId;
        this.type = type;
        this.actionId = actionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getThirdPartyId() {
        return thirdPartyId;
    }

    public void setThirdPartyId(Integer thirdPartyId) {
        this.thirdPartyId = thirdPartyId;
    }

    public User getThirdParty() {
        return thirdParty;
    }

    public void setThirdParty(User thirdParty) {
        this.thirdParty = thirdParty;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", userId=" + userId +
                ", user=" + user +
                ", thirdPartyId=" + thirdPartyId +
                ", thirdParty=" + thirdParty +
                ", type=" + type +
                ", points=" + points +
                ", actionId=" + actionId +
                ", action=" + action +
                ", createdAt=" + createdAt +
                '}';
    }
}
