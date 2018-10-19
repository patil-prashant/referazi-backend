package com.referazi.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.referazi.models.*;
import com.referazi.service.AuthService;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
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

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/resetPassword")
    public Response resetPassword(String request){
        JsonParser parser = new JsonParser();
        JsonObject jsonObject  = (JsonObject) parser.parse(request);
        String email = jsonObject.get("email").getAsString();

        return authService.resetPassword(email);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/changePassword")
    public Response changePassword(@QueryParam("id") Integer userId, @QueryParam("token") String token){

        return authService.changePassword(userId, token);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/savePassword")
    public Response savePassword(@HeaderParam("code") String code, String request){
        JsonParser parser = new JsonParser();
        JsonObject jsonObject  = (JsonObject) parser.parse(request);
        String password = jsonObject.get("password").getAsString();
        String confirmPassword = jsonObject.get("confirmPassword").getAsString();
        return authService.savePassword(code, password, confirmPassword);

    }
}