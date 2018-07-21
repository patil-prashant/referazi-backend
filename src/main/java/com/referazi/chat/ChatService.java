package com.referazi.chat;

import com.referazi.dao.ConversationDao;
import com.referazi.dao.HistoryDao;
import com.referazi.dao.UserDao;
import com.referazi.models.Auth;
import com.referazi.models.Conversation;
import com.referazi.models.User;
import com.referazi.security.SecurityUtils;
import com.referazi.security.SessionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ChatService {

    @Autowired
    @Qualifier("userDao")
    UserDao userDao;

    @Autowired
    @Qualifier("conversationDao")
    ConversationDao conversationDao;

    @Autowired
    @Qualifier("historyDao")
    HistoryDao historyDao;

    @Autowired
    @Qualifier("sessionProvider")
    SessionProvider<Auth> sessionProvider;

    public Response getConversation(Integer receiver) throws Exception {

        User user = SecurityUtils.getUser();

        if (user == null){
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not found").type(MediaType.TEXT_PLAIN).build();
        }

        Conversation conversation = new Conversation();
        conversation.setId(conversationDao.getConversationId(user.getId(), receiver));
        conversation.setUser1(userDao.getMinUserDetails(user.getId()));
        conversation.setUser2(userDao.getMinUserDetails(receiver));
        if(conversation != null){
            conversation.setMessages(historyDao.getMessageHistory(conversation.getId()));
            historyDao.markUnReadAsRead(conversation.getId());
        }

        return Response.ok(conversation, MediaType.APPLICATION_JSON).build();

    }

    public Response getProviderListForChat(){

        User user = SecurityUtils.getUser();

        if (user == null){
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not found").type(MediaType.TEXT_PLAIN).build();
        }

        return Response.ok(userDao.getProviderUserList(), MediaType.APPLICATION_JSON).build();
    }

    public Response getConversationContactList(){

        User user = SecurityUtils.getUser();

        if (user == null){
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not found").type(MediaType.TEXT_PLAIN).build();
        }

        return Response.ok(userDao.getConversationContactList(user.getId()), MediaType.APPLICATION_JSON).build();
    }

}
