package com.referazi.service;

import com.referazi.dao.SeekerDao;
import com.referazi.models.Auth;
import com.referazi.models.Blogger;
import com.referazi.models.Seeker;
import com.referazi.models.User;
import com.referazi.security.SecurityUtils;
import com.referazi.security.SessionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;

public class SeekerService {

    @Autowired
    @Qualifier("seekerDao")
    SeekerDao seekerDao;

    @Autowired
    @Qualifier("sessionProvider")
    SessionProvider<Auth> sessionProvider;

    public Response currentSeeker() throws NoSuchAlgorithmException {

        User user = SecurityUtils.getUser();

        if (user == null){
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not found").type(MediaType.TEXT_PLAIN).build();
        }

        Seeker seeker= seekerDao.findSeekerByUserId(user.getId());

        if (seeker!=null){
            return Response.ok(seeker, MediaType.APPLICATION_JSON).build();
        }else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not registered as Seeker").type(MediaType.TEXT_PLAIN).build();
        }
    }

    public Response deleteSeeker() throws NoSuchAlgorithmException {

        User user = SecurityUtils.getUser();

        if (user == null){
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not found").type(MediaType.TEXT_PLAIN).build();
        }

        Seeker seeker= seekerDao.findSeekerByUserId(user.getId());

        if (seeker!=null){
            seekerDao.deleteSeeker(seeker.getUserId());
            return Response.ok().build();
        }else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not registered as Seeker").type(MediaType.TEXT_PLAIN).build();
        }
    }

    public Response register(Seeker seeker) throws NoSuchAlgorithmException {

        User user = SecurityUtils.getUser();

        if (user == null){
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not found").type(MediaType.TEXT_PLAIN).build();
        }

        if (seekerDao.findSeekerByUserId(user.getId()) != null){
            return Response.status(Response.Status.BAD_REQUEST).entity("User already registered as Seeker").type(MediaType.TEXT_PLAIN).build();
        }else {
            seeker.setUserId(user.getId());
            seekerDao.registerSeeker(seeker);
            seeker = seekerDao.findSeekerByUserId(user.getId());
            return Response.ok(seeker).build();
        }
    }

    public Response updateSeeker(Seeker seeker) throws NoSuchAlgorithmException {

        User user = SecurityUtils.getUser();

        if (user == null){
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not found").type(MediaType.TEXT_PLAIN).build();
        }

        if (seekerDao.findSeekerByUserId(user.getId()) != null){
            seekerDao.updateSeeker(seeker);
            seeker = seekerDao.findSeekerByUserId(user.getId());
            return Response.ok(seeker).build();
        }else {
            return Response.status(Response.Status.BAD_REQUEST).entity("User not registered as Seeker").type(MediaType.TEXT_PLAIN).build();
        }
    }

}
