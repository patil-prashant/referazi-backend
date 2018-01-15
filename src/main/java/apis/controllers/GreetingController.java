package apis.controllers;

import apis.mapper.UserMapper;
import apis.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Component
@Path("/greeting")
public class GreetingController {

    @Autowired
    UserMapper userMapper;


    @GET
    @Produces("application/json")
    @Path("/greet")
    public Users greetings() {
        return userMapper.findUserById(1);
    }
}