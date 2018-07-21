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
    User getMinUserDetails(Integer id);
    String getPassword(String email);
    void updateSeekerStatus(@Param("status")String status, @Param("id")Integer id);
    void updateProviderStatus(@Param("status")String status, @Param("id")Integer id);
    void updateBloggerStatus(@Param("status")String status, @Param("id")Integer id);
    void updateOnlineStatus(@Param("status")Boolean status, @Param("id")Integer id);
    List<User> getProviderUserList();
    List<User> getConversationContactList(@Param("id")Integer id);
}