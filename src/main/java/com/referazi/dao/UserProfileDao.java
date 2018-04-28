package com.referazi.dao;

import com.referazi.models.UserProfile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserProfileDao {
    List<UserProfile> getUserProfile(Integer userId);
    UserProfile findUserProfileByRole(@Param("userId")Integer userId, @Param("role") String role);
    void saveUserProfile(UserProfile userProfile);
}
