package com.referazi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction {

    private Integer id;

    private Integer debtorId;

    private User debtor;

    private Integer creditorId;

    private User creditor;

    private Integer points;

    private Integer actionId;

    private Action action;

    private Date createdAt;

    public Transaction(){

    }

    public Transaction(Integer debtorId, Integer creditorId, Integer actionId) {
        this.debtorId = debtorId;
        this.creditorId = creditorId;
        this.actionId = actionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDebtorId() {
        return debtorId;
    }

    public void setDebtorId(Integer debtorId) {
        this.debtorId = debtorId;
    }

    public User getDebtor() {
        return debtor;
    }

    public void setDebtor(User debtor) {
        this.debtor = debtor;
    }

    public Integer getCreditorId() {
        return creditorId;
    }

    public void setCreditorId(Integer creditorId) {
        this.creditorId = creditorId;
    }

    public User getCreditor() {
        return creditor;
    }

    public void setCreditor(User creditor) {
        this.creditor = creditor;
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
                ", debtorId=" + debtorId +
                ", debtor=" + debtor +
                ", creditorId=" + creditorId +
                ", creditor=" + creditor +
                ", points=" + points +
                ", actionId=" + actionId +
                ", action=" + action +
                ", createdAt=" + createdAt +
                '}';
    }
}
