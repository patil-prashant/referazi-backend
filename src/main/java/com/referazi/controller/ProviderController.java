package com.referazi.controller;

import com.referazi.models.Provider;
import com.referazi.models.Seeker;
import com.referazi.service.ProviderService;
import com.referazi.service.SeekerService;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;

import static javax.ws.rs.core.Response.serverError;

@Controller
@Path("/provider")
public class ProviderController {

    @Inject
    ProviderService providerService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/me")
    public Response current(){
        try {
            return providerService.currentProvider();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity("Internal Server Error").build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/me")
    public Response update(Provider provider){
        try {
            return providerService.updateProvider(provider);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return Response.serverError().entity("Internal Server Error").build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/me")
    public Response delete(Provider provider){
        try {
            return providerService.deleteProvider();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return Response.serverError().entity("Internal Server Error").build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/register")
    public Response register(Provider provider){
        try {
            return providerService.register(provider);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return Response.serverError().entity("Internal Server Error").build();
        }
    }



}