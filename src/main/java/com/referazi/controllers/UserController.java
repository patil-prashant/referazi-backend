package com.referazi.controllers;

import com.referazi.dao.SkillDao;
import com.referazi.dao.UserDao;
import com.referazi.dao.UserProfileDao;
import com.referazi.dao.UserSkillDao;
import com.referazi.models.*;
import com.referazi.security.SessionProvider;
import com.referazi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;

import static javax.ws.rs.core.Response.*;

@Controller
@Path("/user")
public class UserController {

    @Autowired
    UserDao userDao;
    @Autowired
    SkillDao skillDao;
    @Autowired
    UserSkillDao userSkillDao;
    @Autowired
    UserProfileDao userProfileDao;
    @Autowired
    SessionProvider sessionProvider;
    @Inject
    UserService userService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/register")
    public Response register(User user){
        try {
            if (userService.register(user)){
                return ok(user).build();
            }else {
                return status(Status.BAD_REQUEST).build();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response login(Login login){
        try {
            return userService.login(login);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/logout")
    public Response logout(Login login){
        userService.logout();
        return Response.ok().build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/save-profile")
    public Response saveProfile(UserProfile userProfile){
        return userService.saveProfile(userProfile);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/profile")
    public Response profile(@QueryParam("profileType") String profileType){
        return userService.fetchProfile(profileType);
    }
}