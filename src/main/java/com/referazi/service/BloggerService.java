package com.referazi.service;

import com.referazi.dao.BloggerDao;
import com.referazi.dao.UserDao;
import com.referazi.models.Auth;
import com.referazi.models.Blogger;
import com.referazi.models.User;
import com.referazi.security.SecurityUtils;
import com.referazi.security.SessionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;

public class BloggerService {

    @Autowired
    @Qualifier("userDao")
    UserDao userDao;

    @Autowired
    @Qualifier("bloggerDao")
    BloggerDao bloggerDao;

    @Autowired
    @Qualifier("sessionProvider")
    SessionProvider<Auth> sessionProvider;

    public Response currentBlogger() throws Exception {

        User user = SecurityUtils.getUser();

        if (user == null){
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not found").type(MediaType.TEXT_PLAIN).build();
        }

        Blogger blogger = bloggerDao.findBloggerByUserId(user.getId());

        if (blogger!=null){
            return Response.ok(blogger, MediaType.APPLICATION_JSON).build();
        }else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not registered as Blogger").type(MediaType.TEXT_PLAIN).build();
        }
    }

    public Response register(Blogger blogger) throws NoSuchAlgorithmException {

        User user = SecurityUtils.getUser();

        if (user == null){
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not found").type(MediaType.TEXT_PLAIN).build();
        }

        if (bloggerDao.findBloggerByUserId(user.getId()) != null){
            return Response.status(Response.Status.BAD_REQUEST).entity("User already registered as Blogger").type(MediaType.TEXT_PLAIN).build();
        }else {
            blogger = new Blogger();
            blogger.setUserId(user.getId());
            bloggerDao.registerBlogger(blogger);
            userDao.updateBloggerStatus("true", user.getId());
            blogger = bloggerDao.findBloggerByUserId(user.getId());
            return Response.ok(blogger).build();
        }
    }

}
