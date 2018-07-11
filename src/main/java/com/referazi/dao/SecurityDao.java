package com.referazi.dao;

import com.referazi.models.Auth;
import org.apache.ibatis.annotations.Param;

public interface SecurityDao
{
    Auth getAuthDetails(@Param("userId") Integer userId, @Param("token") String token);

    Auth getAuthDetailsByToken(@Param("token") String token);

    void insertUserToken(@Param("userId") Integer userId, @Param("token") String token);

    void deleteUserToken(@Param("userId") Integer userId, @Param("token") String token);

}