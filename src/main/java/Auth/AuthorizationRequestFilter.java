package Auth;

import apis.providers.SessionProvider;
import apis.providers.SessionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthorizationRequestFilter implements ContainerRequestFilter {
    @Autowired
    SessionProviderImpl sessionProvider;
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        if (!containerRequestContext.getUriInfo().getPath().contains("private")){
            System.out.println(sessionProvider.session("email"));
            if (sessionProvider.session("email") == null){
                containerRequestContext.abortWith(Response
                        .status(Response.Status.UNAUTHORIZED)
                        .entity("User cannot access the resource.")
                        .build());
            }
        }
    }
}