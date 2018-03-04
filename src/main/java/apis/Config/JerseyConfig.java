package apis.Config;

import Auth.AuthorizationRequestFilter;
import Auth.CrossDomainFilter;
import apis.controllers.UserController;
import org.apache.catalina.filters.CorsFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig(){
        register(UserController.class);
        register(AuthorizationRequestFilter.class);
        register(CrossDomainFilter.class);
    }
}
