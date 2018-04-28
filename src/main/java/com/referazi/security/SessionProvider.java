package com.referazi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class SessionProvider {
    @Autowired
    private HttpSession httpSession;

    public void session(String key,String value){
        httpSession.setAttribute(key,value);
    }

    public String session(String key){
        return (String) httpSession.getAttribute(key);
    }

    public void clear(){
        httpSession.invalidate();
    }
}
