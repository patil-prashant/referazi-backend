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
    @Qualifier("sessionProvider")
    SessionProvider<Auth> sessionProvider;

    public Response currentUser() throws Exception {

        User user = SecurityUtils.getUser();

        if (user!=null){
            return Response.ok(user, MediaType.APPLICATION_JSON).build();
        }else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not found").type(MediaType.TEXT_PLAIN).build();
        }
    }

    public boolean register(User user) throws NoSuchAlgorithmException {
        if (userDao.findUserByEmail(user.getEmail()) != null){
            return false;
        }else {
//            String password = user.getPassword();
//            MessageDigest digest = MessageDigest.getInstance("SHA-256");
//            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
//            String encodedHashPwd = new String(encodedHash,StandardCharsets.UTF_8);
//            user.setPassword(encodedHashPwd);
            userDao.insertUser(user);
            return true;
        }
    }

}
