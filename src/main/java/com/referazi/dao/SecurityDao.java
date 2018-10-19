package com.referazi.dao;

import com.referazi.models.Auth;
import com.referazi.models.PasswordResetToken;
import com.referazi.models.User;
import org.apache.ibatis.annotations.Param;

public interface SecurityDao
{
    Auth getAuthDetails(@Param("userId") Integer userId, @Param("token") String token);

    Auth getAuthDetailsByToken(@Param("token") String token);

    void insertUserToken(@Param("userId") Integer userId, @Param("token") String token);

    void deleteUserToken(@Param("userId") Integer userId, @Param("token") String token);

    void resetPassordRequest(@Param("userId") Integer userId, @Param("token") String token);

    PasswordResetToken getPasswordResetReq(@Param("userId") Integer userId, @Param("token") String token);

    User getUserByPasswordResetToken(@Param("token") String token);
}