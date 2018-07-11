package com.referazi.controller;

import com.referazi.models.*;
import com.referazi.service.AuthService;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;

import static javax.ws.rs.core.Response.*;

@Controller
@Path("/auth")
public class AuthController {

    @Inject
    AuthService authService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response login(Login login){
        try {
            return authService.login(login);
        } catch (Exception e) {
            e.printStackTrace();
            return serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/logout")
    public Response logout(Login login){
        authService.logout();
        return Response.ok().build();
    }

}