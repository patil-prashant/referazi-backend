package apis.Config;

import Auth.AuthorizationRequestFilter;
import apis.controllers.UserController;
import apis.providers.SessionProviderImpl;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig(){
        register(UserController.class);
        register(AuthorizationRequestFilter.class);
    }
}
