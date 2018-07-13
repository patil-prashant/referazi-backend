package com.referazi.controller;

import com.referazi.models.Blogger;
import com.referazi.models.Seeker;
import com.referazi.service.BloggerService;
import com.referazi.service.SeekerService;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;

import static javax.ws.rs.core.Response.serverError;

@Controller
@Path("/seeker")
public class SeekerController {

    @Inject
    SeekerService seekerService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/me")
    public Response current(){
        try {
            return seekerService.currentSeeker();
        } catch (Exception e) {
            e.printStackTrace();
            return serverError().entity(e.getMessage()).build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/me")
    public Response update(Seeker seeker){
        try {
            return seekerService.updateSeeker(seeker);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/me")
    public Response delete(Seeker seeker){
        try {
            return seekerService.deleteSeeker();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/register")
    public Response register(Seeker seeker){
        try {
            return seekerService.register(seeker);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return Response.serverError().entity(e.getMessage()).build();
        }
    }



}