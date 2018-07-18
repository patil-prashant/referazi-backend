package com.referazi.controller;

import com.referazi.models.User;
import com.referazi.service.UserService;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.security.NoSuchAlgorithmException;

import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.serverError;
import static javax.ws.rs.core.Response.status;

@Controller
@Path("/user")
public class UserController {

    @Inject
    UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/me")
    public Response current(){
        try {
            return userService.currentUser();
        } catch (Exception e) {
            e.printStackTrace();
            return serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/register")
    public Response register(User user){
        try {
            return userService.register(user);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

}