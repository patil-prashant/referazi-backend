package com.referazi.config;

import com.referazi.controllers.DataController;
import com.referazi.security.AuthorizationRequestFilter;
import com.referazi.security.CrossDomainFilter;
import com.referazi.controllers.UserController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig(){
        register(UserController.class);
        register(DataController.class);
        register(AuthorizationRequestFilter.class);
        register(CrossDomainFilter.class);
    }
}
