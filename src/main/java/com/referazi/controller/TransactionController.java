package com.referazi.controller;

import com.referazi.models.Transaction;
import com.referazi.service.TransactionService;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.serverError;

@Controller
@Path("/transaction")
public class TransactionController {

    @Inject
    TransactionService transactionService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAllTransactions(){
        try {
            return transactionService.getAllTransactions();
        } catch (Exception e) {
            e.printStackTrace();
            return serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/balance")
    public Response getBalancePoints(){
        try {
            return transactionService.getUserPoints();
        } catch (Exception e) {
            e.printStackTrace();
            return serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/wallet")
    public Response getWalletDetails(){
        try {
            return transactionService.getWalletDetails();
        } catch (Exception e) {
            e.printStackTrace();
            return serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Response register(Transaction transaction){
        try {
            return transactionService.insertTransaction(transaction);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

}