package com.referazi.chat;

import com.referazi.models.ProfileType;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.serverError;

@Controller
@Path("/chat")
public class ChatController {

    @Inject
    ChatService chatService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/queries")
    public Response getQueriesList(@QueryParam("profile-type")String profileType){
        try {
            return chatService.getQueryList(ProfileType.valueOf(profileType));
        } catch (Exception e) {
            e.printStackTrace();
            return serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/answers/{queryId}")
    public Response getAnswerList(@PathParam("queryId")Integer queryId){
        try {
            return chatService.getAnswerList(queryId);
        } catch (Exception e) {
            e.printStackTrace();
            return serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/conversation/{receiverId}")
    public Response getHistory(@PathParam("receiverId")Integer receiverId){
        try {
            return chatService.getConversation(receiverId);
        } catch (Exception e) {
            e.printStackTrace();
            return serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/provider")
    public Response getProvider(){
        try {
            return chatService.getProviderListForChat();
        } catch (Exception e) {
            e.printStackTrace();
            return serverError().entity(e.getMessage()).build();
        }
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/recentChats")
    public Response getConversationContactList(){
        try {
            return chatService.getConversationContactList();
        } catch (Exception e) {
            e.printStackTrace();
            return serverError().entity(e.getMessage()).build();
        }
    }
}