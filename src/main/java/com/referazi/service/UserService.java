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

public class UserService {

    @Autowired
    @Qualifier("userDao")
    UserDao userDao;

    @Autowired
    @Qualifier("securityDao")
    SecurityDao securityDao;

    @Autowired
    @Qualifier("sessionProvider")
    SessionProvider<Auth> sessionProvider;

    @Autowired
    @Qualifier("securityManager")
    SecurityManager securityManager;

    public Response currentUser() throws Exception {

        User user = SecurityUtils.getUser();

        if (user!=null){
            return Response.ok(user, MediaType.APPLICATION_JSON).build();
        }else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not found").type(MediaType.TEXT_PLAIN).build();
        }
    }

    public Response register(User user) throws NoSuchAlgorithmException {
        if (userDao.findUserByEmail(user.getEmail()) != null){
            return Response.status(Response.Status.BAD_REQUEST).entity("User already registered with the Email-ID: "+user.getEmail()).type(MediaType.TEXT_PLAIN).build();
        }else {
//            String password = user.getPassword();
//            MessageDigest digest = MessageDigest.getInstance("SHA-256");
//            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
//            String encodedHashPwd = new String(encodedHash,StandardCharsets.UTF_8);
//            user.setPassword(encodedHashPwd);
            userDao.insertUser(user);
            user = userDao.findUserByEmail(user.getEmail());
            user.setPassword(null);
            Auth auth = new Auth();
            String token = securityManager.generateAccessToken();
            auth.setToken(token);
            //TODO: manage transactional
            securityDao.insertUserToken(user.getId(), token);
            auth = securityDao.getAuthDetails(user.getId(), token);
            auth.setUser(user);
            sessionProvider.addSession(token,auth);
            return Response.status(Response.Status.CREATED).entity(auth).build();
        }
    }

}
