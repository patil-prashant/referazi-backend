package com.referazi.dao;

import com.referazi.models.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao
{
    void insertUser(User user);
    User findUserById(Integer id);
    List<User> findAllUsers();
    User findUserByEmail(String email);
    String getPassword(String email);
    void updateSeekerStatus(@Param("status")String ststus, @Param("id")Integer id);
    void updateProviderStatus(@Param("status")String ststus, @Param("id")Integer id);
    void updateBloggerStatus(@Param("status")String ststus, @Param("id")Integer id);
}