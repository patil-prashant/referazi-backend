package apis.providers;

import apis.mapper.UserMapper;
import apis.models.Login;
import apis.models.User;
import org.springframework.stereotype.Service;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {
    @Inject
    UserMapper userMapper;
    @Inject
    SessionProvider sessionProvider;

    public boolean register(User user) throws NoSuchAlgorithmException {
        if (userMapper.findUserByEmail(user.getEmail()) != null){
            return false;
        }else {
            String password = user.getPassword();
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            String encodedHashPwd = new String(encodedHash,StandardCharsets.UTF_8);
            user.setPassword(encodedHashPwd);
            userMapper.insertUser(user);
            return true;
        }
    }

    public Response login(Login login) throws NoSuchAlgorithmException {
        User user = userMapper.findUserByEmail(login.getEmail());
        if (user!=null){
            String password = userMapper.getPassword(login.getEmail());
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(login.getPassword().getBytes(StandardCharsets.UTF_8));
            String encodedHashString = new String(encodedHash,StandardCharsets.UTF_8);
            System.out.println(encodedHashString);
            System.out.println(password);
            if (encodedHashString.equals(password)){
                sessionProvider.session("email",user.getEmail());
                sessionProvider.session("id", String.valueOf(user.getId()));
                return Response.ok("logged in successfully", MediaType.TEXT_PLAIN_TYPE).build();
            }else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Password mis-matched").build();
            }
        }else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not found").build();
        }
    }
    public void logout() {
        sessionProvider.clear();
    }
}
