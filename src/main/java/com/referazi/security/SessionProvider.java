package com.referazi.security;

import java.util.HashMap;
import java.util.Map;

public class SessionProvider<T> {

    private Class<T> type;

    public SessionProvider(Class<T> type) {
        this.type = type;
    }

    private Map<String, T> map = new HashMap<>();

    public SessionProvider(Map<String, T> map) {
        this.map = map;
    }

    public void addSession(String id, T t) {
        map.put(id, t);
    }

    public void updateSession(String id, T t) {
        map.put(id, t);
    }

    public T getSession(String id) {
        return map.get(id);
    }

    public void clearSession() {
        map.clear();
    }

    public void removeSession(String id) {
        map.remove(id);
    }

}
