package apis.controllers;

import apis.mapper.UserMapper;
import apis.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @Autowired
    UserMapper userMapper;


    @RequestMapping("/greetings")
    public Users greetings() {
        return userMapper.findUserById(1);
    }
}