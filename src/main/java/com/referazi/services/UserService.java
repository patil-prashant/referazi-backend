package com.referazi.services;

import com.referazi.dao.UserDao;
import com.referazi.dao.UserProfileDao;
import com.referazi.dao.UserSkillDao;
import com.referazi.models.Login;
import com.referazi.models.User;
import com.referazi.models.UserProfile;
import com.referazi.models.UserSkill;
import com.referazi.security.SessionProvider;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService {
    @Inject
    UserDao userDao;
    @Inject
    UserProfileDao userProfileDao;
    @Inject
    UserSkillDao userSkillDao;
    @Inject
    SessionProvider sessionProvider;

    public boolean register(User user) throws NoSuchAlgorithmException {
        if (userDao.findUserByEmail(user.getEmail()) != null){
            return false;
        }else {
//            String password = user.getPassword();
//            MessageDigest digest = MessageDigest.getInstance("SHA-256");
//            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
//            String encodedHashPwd = new String(encodedHash,StandardCharsets.UTF_8);
//            user.setPassword(encodedHashPwd);
            userDao.insertUser(user);
            return true;
        }
    }

    public Response login(Login login) throws NoSuchAlgorithmException {
        User user = userDao.findUserByEmail(login.getEmail());
        if (user!=null){
            String password = userDao.getPassword(login.getEmail());
//            List<UserProfile> userProfiles = userProfileDao.getUserProfile(user.getId());
//            user.setUserProfiles(userProfiles);
//            MessageDigest digest = MessageDigest.getInstance("SHA-256");
//            byte[] encodedHash = digest.digest(login.getPassword().getBytes(StandardCharsets.UTF_8));
//            String encodedHashString = new String(encodedHash,StandardCharsets.UTF_8);
//            System.out.println(encodedHashString);
//            System.out.println(password);
            if (login.getPassword().equals(password)){
                sessionProvider.session("email",user.getEmail());
                sessionProvider.session("id", String.valueOf(user.getId()));
                return Response.ok(user, MediaType.APPLICATION_JSON).build();
            }else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Password mis-matched").type(MediaType.TEXT_PLAIN).build();
            }
        }else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not found").type(MediaType.TEXT_PLAIN).build();
        }
    }

    public Response saveProfile(UserProfile profile){
        if(profile != null){
            userProfileDao.saveUserProfile(profile);

            for (String skill : profile.getSkills()) {
                UserSkill userSkill = new UserSkill(profile.getUserId(), profile.getRole(), skill);
                userSkillDao.addUserSkill(userSkill);
            }
            return Response.ok(profile, MediaType.APPLICATION_JSON).build();
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    public Response fetchProfile(String profileType){

        String userId = sessionProvider.session("id");

        if(!StringUtils.isEmpty(profileType) && !StringUtils.isEmpty(userId)) {

            UserProfile userProfile = userProfileDao.findUserProfileByRole(Integer.valueOf(userId), profileType);

            userProfile.setSkills(userSkillDao.getUserSkills(Integer.valueOf(userId), profileType));

            return Response.ok(userProfile, MediaType.APPLICATION_JSON).build();
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    public void logout() {
        sessionProvider.clear();
    }
}
