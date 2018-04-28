package com.referazi.dao;

import com.referazi.models.User;
import java.util.List;

public interface UserDao
{
    void insertUser(User user);
    User findUserById(Integer id);
    List<User> findAllUsers();
    User findUserByEmail(String email);
    String getPassword(String email);
}