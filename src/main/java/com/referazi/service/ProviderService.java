package com.referazi.service;

import com.referazi.dao.ProviderDao;
import com.referazi.dao.SeekerDao;
import com.referazi.dao.UserDao;
import com.referazi.models.Auth;
import com.referazi.models.Provider;
import com.referazi.models.Seeker;
import com.referazi.models.User;
import com.referazi.security.SecurityUtils;
import com.referazi.security.SessionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;

public class ProviderService {

    @Autowired
    @Qualifier("userDao")
    UserDao userDao;

    @Autowired
    @Qualifier("providerDao")
    ProviderDao providerDao;

    @Autowired
    @Qualifier("sessionProvider")
    SessionProvider<Auth> sessionProvider;

    public Response currentProvider() throws NoSuchAlgorithmException {

        User user = SecurityUtils.getUser();

        if (user == null){
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not found").type(MediaType.TEXT_PLAIN).build();
        }

        Provider provider= providerDao.findProviderByUserId(user.getId());

        if (provider!=null){
            return Response.ok(provider, MediaType.APPLICATION_JSON).build();
        }else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not registered as Provider").type(MediaType.TEXT_PLAIN).build();
        }
    }

    public Response deleteProvider() throws NoSuchAlgorithmException {

        User user = SecurityUtils.getUser();

        if (user == null){
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not found").type(MediaType.TEXT_PLAIN).build();
        }

        Provider provider= providerDao.findProviderByUserId(user.getId());

        if (provider!=null){
            providerDao.deleteProvider(provider.getUserId());
            userDao.updateProviderStatus("false", user.getId());
            return Response.ok().build();
        }else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not registered as Provider").type(MediaType.TEXT_PLAIN).build();
        }
    }

    public Response register(Provider provider) throws NoSuchAlgorithmException {

        User user = SecurityUtils.getUser();

        if (user == null){
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not found").type(MediaType.TEXT_PLAIN).build();
        }

        if (providerDao.findProviderByUserId(user.getId()) != null){
            return Response.status(Response.Status.BAD_REQUEST).entity("User already registered as Provider").type(MediaType.TEXT_PLAIN).build();
        }else {
            provider.setUserId(user.getId());
            providerDao.registerProvider(provider);
            userDao.updateProviderStatus("true", user.getId());
            provider = providerDao.findProviderByUserId(user.getId());
            return Response.ok(provider).build();
        }
    }

    public Response updateProvider(Provider provider) throws NoSuchAlgorithmException {

        User user = SecurityUtils.getUser();

        if (user == null){
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not found").type(MediaType.TEXT_PLAIN).build();
        }

        if (providerDao.findProviderByUserId(user.getId()) != null){
            providerDao.updateProvider(provider);
            provider = providerDao.findProviderByUserId(user.getId());
            return Response.ok(provider).build();
        }else {
            return Response.status(Response.Status.BAD_REQUEST).entity("User not registered as Provider").type(MediaType.TEXT_PLAIN).build();
        }
    }

}
