package com.referazi.controller;

import com.referazi.service.ActionService;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.serverError;

@Controller
@Path("/action")
public class ActionController {

    @Inject
    ActionService actionService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAllActions(){
        try {
            return actionService.getAllActions();
        } catch (Exception e) {
            e.printStackTrace();
            return serverError().entity(e.getMessage()).build();
        }
    }

}