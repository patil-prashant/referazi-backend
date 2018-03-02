package apis.mapper;

import apis.models.User;
import java.util.List;

public interface UserMapper
{
    void insertUser(User user);
    User findUserById(Integer id);
    List<User> findAllUsers();
    User findUserByEmail(String email);
    String getPassword(String email);
}