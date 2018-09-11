package com.referazi.service;

import com.referazi.dao.SecurityDao;
import com.referazi.dao.UserDao;
import com.referazi.manager.SecurityManager;
import com.referazi.models.Auth;
import com.referazi.models.Login;
import com.referazi.models.PasswordResetToken;
import com.referazi.models.User;
import com.referazi.security.SecurityUtils;
import com.referazi.security.SessionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.UUID;

public class AuthService {

    String domain;

    String basePath;

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

    @Autowired
    JavaMailSender javaMailSender;

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

    public Response resetPassword(String email){

        if(StringUtils.isEmpty(email)){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        User user = userDao.findUserByEmail(email);

        if(user == null){
            return Response.status(Response.Status.BAD_REQUEST).entity("Email ID not registered.").build();
        }

        String token = UUID.randomUUID().toString();

        securityDao.resetPassordRequest(user.getId(), token);

        javaMailSender.send(new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessageObj) throws MessagingException {
                MimeMessageHelper messageObj = new MimeMessageHelper(mimeMessageObj, true, "UTF-8");
                messageObj.setFrom("prashantpatil.ws@gmail.com");
                messageObj.setTo("p3patildahanu@gmail.com");
                messageObj.setSubject("Reset Password");
                messageObj.setText(domain+basePath+"/api/auth/changePassword?id="+user.getId()+"&token="+token, true);;
            }
        });

        return Response.ok().build();
    }

    public Response changePassword(Integer userId, String token){

        if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(token)){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        PasswordResetToken passwordResetToken = securityDao.getPasswordResetReq(userId, token);

        if(passwordResetToken == null){
            return Response.status(Response.Status.BAD_REQUEST).entity("Email ID not registered.").build();
        }

        return Response.seeOther(URI.create(domain+basePath+"/app/changePassword?code="+token)).build();
    }

    public Response savePassword(String code, String password, String confirmPassword){

        if(StringUtils.isEmpty(code) || StringUtils.isEmpty(password) || StringUtils.isEmpty(confirmPassword)){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        if(!password.equals(confirmPassword)){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        User user = securityDao.getUserByPasswordResetToken(code);

        if(user == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        userDao.updatePassword(password, user.getId());

        return Response.ok().build();
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }
}
