package com.referazi.controller;

import com.referazi.models.Blogger;
import com.referazi.service.BloggerService;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;

import static javax.ws.rs.core.Response.serverError;

@Controller
@Path("/blogger")
public class BloggerController {

    @Inject
    BloggerService bloggerService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/me")
    public Response current(){
        try {
            return bloggerService.currentBlogger();
        } catch (Exception e) {
            e.printStackTrace();
            return serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/register")
    public Response register(Blogger blogger){
        try {
            return bloggerService.register(blogger);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

}