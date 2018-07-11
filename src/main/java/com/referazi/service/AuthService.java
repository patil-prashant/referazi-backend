package com.referazi.service;

import com.referazi.dao.SecurityDao;
import com.referazi.dao.UserDao;
import com.referazi.manager.SecurityManager;
import com.referazi.models.Auth;
import com.referazi.models.Login;
import com.referazi.models.User;
import com.referazi.security.SecurityUtils;
import com.referazi.security.SessionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;

public class AuthService {

    @Autowired
    @Qualifier("userDao")
    UserDao userDao;

    @Autowired
    @Qualifier("securityManager")
    SecurityManager securityManager;

    @Autowired
    @Qualifier("securityDao")
    SecurityDao securityDao;

    @Autowired
    @Qualifier("sessionProvider")
    SessionProvider<Auth> sessionProvider;



    public Response login(Login login) throws Exception {
        User user = userDao.findUserByEmail(login.getEmail());
        if (user!=null){
            String password = userDao.getPassword(login.getEmail());
            //TODO: use encoded pass
            if (login.getPassword().equals(password)){
                Auth auth = new Auth();
                String token = securityManager.generateAccessToken();
                auth.setToken(token);
                //TODO: manage transactional
                securityDao.insertUserToken(user.getId(), token);

                auth = securityDao.getAuthDetails(user.getId(), token);
                auth.setUser(user);

                sessionProvider.addSession(token,auth);

                return Response.ok(auth, MediaType.APPLICATION_JSON).build();
            }else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Password mis-matched").type(MediaType.TEXT_PLAIN).build();
            }
        }else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not found").type(MediaType.TEXT_PLAIN).build();
        }
    }

    public void logout(){
        Auth auth = SecurityUtils.getAuthDetails();

        User user = auth.getUser();

        securityDao.deleteUserToken(user.getId(), auth.getToken());

        sessionProvider.removeSession(auth.getToken());

        SecurityUtils.resetUserAuthInfo();
    }

}
