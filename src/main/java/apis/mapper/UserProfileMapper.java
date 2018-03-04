package apis.mapper;

import apis.models.UserProfile;

import java.util.List;

public interface UserProfileMapper {
    List<UserProfile> getUserProfile(Integer userId);
    UserProfile findUserProfileById(Integer id);
    void insertUser(UserProfile userProfile);
}
