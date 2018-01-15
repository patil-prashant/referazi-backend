package apis.mapper;

import apis.models.Users;
import java.util.List;

public interface UserMapper
{
    void insertUser(Users user);
    Users findUserById(Integer id);
    List<Users> findAllUsers();
}