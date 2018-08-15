package com.referazi.service;

import com.referazi.dao.ActionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.ws.rs.core.Response;

public class ActionService {

    @Autowired
    @Qualifier("actionDao")
    ActionDao actionDao;

    public Response getAllActions(){

        return Response.ok(actionDao.getActions()).build();
    }
}
