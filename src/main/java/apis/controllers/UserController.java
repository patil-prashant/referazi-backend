package apis.controllers;

import apis.mapper.SkillMapper;
import apis.mapper.UserMapper;
import apis.mapper.UserProfileMapper;
import apis.models.Skill;
import apis.models.UserProfile;
import apis.providers.SessionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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
    SessionProviderImpl sessionProvider;

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
        System.out.println("EEEE: "+sessionProvider.session("email"));
        return sessionProvider.session("email");
        /*return "hi";*/
    }
}