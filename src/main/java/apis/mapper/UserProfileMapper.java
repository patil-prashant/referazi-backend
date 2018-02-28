package apis.mapper;

import apis.models.UserProfile;

public interface UserProfileMapper {
    UserProfile getUserProfile(Integer userId);
    UserProfile findUserProfileById(Integer id);
    void insertUser(UserProfile userProfile);
}
