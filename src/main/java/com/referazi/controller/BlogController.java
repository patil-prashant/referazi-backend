package com.referazi.controller;

import com.referazi.service.BlogService;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;

import static javax.ws.rs.core.Response.Status;

@Controller
@Path("/blog")
public class BlogController {

    @Inject
    BlogService blogService;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/")
    public Response postBlog(@FormDataParam("image") InputStream uploadedInputStream,
                             @FormDataParam("image") FormDataContentDisposition fileDetail,
                             @FormDataParam("title") String title,
                             @FormDataParam("description") String description){
        try {

            blogService.postBlog(uploadedInputStream, fileDetail.getFileName(), title, description);

            return Response.status(Status.OK).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity("Internal Server Exception").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response getAllBlogs(){
        try{
            return Response.status(Status.OK).entity(blogService.getAllBlogs()).build();
        }catch (Exception e){
            e.printStackTrace();
            return Response.serverError().entity("Internal Server Exception").build();
        }
    }

}