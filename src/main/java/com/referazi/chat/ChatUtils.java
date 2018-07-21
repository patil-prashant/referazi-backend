package com.referazi.chat;

import com.referazi.dao.ConversationDao;
import com.referazi.dao.HistoryDao;
import com.referazi.dao.UserDao;

import javax.websocket.Session;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ChatUtils {

    private static HistoryDao historyDao;

    private static ConversationDao conversationDao;

    private static UserDao userDao;

    private static Map<Integer, Session> socketSessionMap = Collections.synchronizedMap(new HashMap<Integer, Session>());

    private ChatUtils() {

    }

    public static void setAllDao(ConversationDao conversationDao, HistoryDao historyDao, UserDao userDao){
        ChatUtils.historyDao = historyDao;
        ChatUtils.conversationDao = conversationDao;
        ChatUtils.userDao = userDao;
    }

    public static void addSocketSession(Integer id, Session session){
        ChatUtils.socketSessionMap.put(id, session);
    }

    public static Session getSocketSession(Integer id){
        return ChatUtils.socketSessionMap.get(id);
    }

    public static void removeSocketSession(Integer id){
        ChatUtils.socketSessionMap.remove(id);
    }

    public static HistoryDao getHistoryDao() {
        return historyDao;
    }

    public static void setHistoryDao(HistoryDao historyDao) {
        ChatUtils.historyDao = historyDao;
    }

    public static ConversationDao getConversationDao() {
        return conversationDao;
    }

    public static void setConversationDao(ConversationDao conversationDao) {
        ChatUtils.conversationDao = conversationDao;
    }

    public static UserDao getUserDao() {
        return userDao;
    }

    public static void setUserDao(UserDao userDao) {
        ChatUtils.userDao = userDao;
    }
}

