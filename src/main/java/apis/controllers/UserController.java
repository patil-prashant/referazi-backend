package apis.controllers;

import apis.mapper.SkillMapper;
import apis.mapper.UserMapper;
import apis.mapper.UserProfileMapper;
import apis.models.Login;
import apis.models.Skill;
import apis.models.User;
import apis.models.UserProfile;
import apis.providers.SessionProvider;
import apis.providers.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;

import static javax.ws.rs.core.Response.*;

@Controller
@Path("/user")
public class UserController {

    @Autowired
    UserMapper userMapper;
    @Autowired
    SkillMapper skillMapper;
    @Autowired
    UserProfileMapper userProfileMapper;
    @Autowired
    SessionProvider sessionProvider;
    @Inject
    UserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/addSkills")
    public Skill addSkills(Skill skill){
        skillMapper.addUserSkill(skill);
        return skill;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/saveProfile")
    public UserProfile saveProfile(UserProfile userProfile){
        userProfileMapper.insertUser(userProfile);
        return userProfile;
    }

    @GET
    @Path("/tempx")
    public String setSession(){
        sessionProvider.session("email","pdharane@gmail.com");
        return sessionProvider.session("email");
        /*return "hi";*/
    }

    @GET
    @Path("/tempy")
    public String clearSession(){
        sessionProvider.clear();
        return sessionProvider.session("email");
        /*return "hi";*/
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/register")
    public Response register(User user){
        try {
            if (userService.register(user)){
                return ok(user).build();
            }else {
                return status(Status.BAD_REQUEST).build();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response login(Login login){
        try {
            return userService.login(login);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/logout")
    public Response logout(Login login){
        userService.logout();
        return Response.ok().build();
    }
}