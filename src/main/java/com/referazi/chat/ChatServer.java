package com.referazi.chat;

import com.google.gson.Gson;
import com.referazi.models.Auth;
import com.referazi.models.Conversation;
import com.referazi.models.History;
import com.referazi.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/messenger/{receiver}")
@ApplicationScope
public class ChatServer {

    private static final Logger log = LoggerFactory.getLogger(ChatServer.class);

    @OnOpen
    public void onOpen(Session session){
        Auth auth = SecurityUtils.getAuthDetails();
        log.info("{} connected",auth.getUserId());
        ChatUtils.addSocketSession(auth.getUserId(), session);
        ChatUtils.getUserDao().updateOnlineStatus(true, auth.getUserId());
    }

    @OnClose
    public void onClose(){
        Auth auth = SecurityUtils.getAuthDetails();
        ChatUtils.removeSocketSession(auth.getUserId());
        ChatUtils.getUserDao().updateOnlineStatus(false, auth.getUserId());
        log.info("Closed Connection for {}...", auth.getUserId());
    }

    @OnMessage
    public void onMessage(String textMessageHistory, @PathParam("receiver")Integer receiver) throws IOException, EncodeException {
        History history = new Gson().fromJson(textMessageHistory, History.class);
        Auth auth = SecurityUtils.getAuthDetails();
        Conversation conversation = ChatUtils.getConversationDao().getConversation(auth.getUserId(), receiver);
        if (conversation == null){
            conversation.setUser1Id(auth.getUserId());
            conversation.setUser2Id(receiver);
            ChatUtils.getConversationDao().createConversation(conversation);
            conversation = ChatUtils.getConversationDao().getConversation(auth.getUserId(), receiver);
        }
        history.setConversationId(conversation.getId());
        Session socketSession = ChatUtils.getSocketSession(receiver);
        if (socketSession != null) {
            history.setReadStatus(true);
            ChatUtils.getHistoryDao().insertMessageHistory(history);
            history = ChatUtils.getHistoryDao().getHistoryMessageById(history.getId());
            socketSession.getBasicRemote().sendText(new Gson().toJson(history).toString());
        }else {
            history.setReadStatus(false);
            ChatUtils.getHistoryDao().insertMessageHistory(history);
        }
    }

    @OnError
    public void onError(Throwable e){
        e.printStackTrace();
    }

}