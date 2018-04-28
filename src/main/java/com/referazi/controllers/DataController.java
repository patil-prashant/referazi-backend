package com.referazi.controllers;

import com.referazi.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Controller
@Path("/data")
public class DataController {

    @Autowired
    DataService dataService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/skills")
    public Response getSkills(@QueryParam("profileType") String profileType){
        return dataService.getSkills(profileType);
    }

}