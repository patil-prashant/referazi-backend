package com.referazi.service;

import com.referazi.dao.ActionDao;
import com.referazi.dao.TransactionDao;
import com.referazi.dao.UserDao;
import com.referazi.models.*;
import com.referazi.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class TransactionService {

    @Autowired
    @Qualifier("transactionDao")
    TransactionDao transactionDao;

    @Autowired
    @Qualifier("userDao")
    UserDao userDao;

    @Autowired
    @Qualifier("actionDao")
    ActionDao actionDao;

    public Response getAllTransactions(){

        User user = SecurityUtils.getUser();

        if (user == null){
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not found.").type(MediaType.TEXT_PLAIN).build();
        }

        return Response.ok(transactionDao.getTransactionsOfUser(user.getId()), MediaType.APPLICATION_JSON).build();

    }

    public Response insertTransaction(Transaction transaction){

        User user = SecurityUtils.getUser();

        if (user == null){
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not found.").type(MediaType.TEXT_PLAIN).build();
        }

        if(!transaction.getUserId().equals(user.getId())){
            return Response.status(Response.Status.BAD_REQUEST).entity("Incorrect UserId.").type(MediaType.TEXT_PLAIN).build();
        }

        if(transaction.getType().equals(TransactionType.DEBIT) && !isSufficientBalance(user.getId(), transaction.getActionId())){
            return Response.status(Response.Status.BAD_REQUEST).entity("Insufficient balance.").type(MediaType.TEXT_PLAIN).build();
        }

        transactionDao.insertTransaction(transaction);

        return Response.ok().build();

    }

    public Response getUserPoints(){

        User user = SecurityUtils.getUser();

        if (user == null){
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not found.").type(MediaType.TEXT_PLAIN).build();
        }

        return Response.ok(transactionDao.getPointsOfUser(user.getId()), MediaType.APPLICATION_JSON).build();

    }

    public Response getWalletDetails(){

        User user = SecurityUtils.getUser();

        if (user == null){

            return Response.status(Response.Status.UNAUTHORIZED).entity("User not found.").type(MediaType.TEXT_PLAIN).build();
        }

        Wallet wallet = new Wallet(user, transactionDao.getPointsOfUser(user.getId()), transactionDao.getTransactionsOfUser(user.getId()));

        return Response.ok(wallet, MediaType.APPLICATION_JSON).build();

    }

    public Boolean isSufficientBalance(Integer userId, Integer actionId){

        Action action = actionDao.getActionById(actionId);

        Integer availablePoints = transactionDao.getPointsOfUser(userId) - action.getPoints();

        if((availablePoints < 0)){
            return false;
        }

        return true;
    }

}
