package com.referazi.services;

import com.referazi.dao.SkillDao;
import com.referazi.security.SessionProvider;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Service
public class DataService {
    @Inject
    SkillDao skillDao;
    @Inject
    SessionProvider sessionProvider;

    public Response getSkills(String profileType){

        if(profileType != null){
            return Response.ok(skillDao.getSkills(profileType), MediaType.APPLICATION_JSON).build();
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    public void logout() {
        sessionProvider.clear();
    }
}
