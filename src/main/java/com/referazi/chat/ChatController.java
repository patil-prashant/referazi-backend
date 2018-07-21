package com.referazi.chat;

import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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