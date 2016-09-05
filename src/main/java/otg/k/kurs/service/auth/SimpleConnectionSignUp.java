package otg.k.kurs.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import otg.k.kurs.domain.Role;
import otg.k.kurs.domain.User;
import otg.k.kurs.repository.UserRepository;
import otg.k.kurs.service.UserService;

import java.util.Arrays;
import java.util.UUID;

@Component
@Transactional
public class SimpleConnectionSignUp implements ConnectionSignUp {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Override
    public String execute(Connection<?> connection) {
        User user;
        String email = connection.fetchUserProfile().getEmail();
        if(email != null && (user = userRepository.findByEmail(email)) != null){
            user.setThirdPartyId(connection.createData().getProviderId() + connection.createData().getProviderUserId());
        } else {
            user = userService.createSocialUser(connection);
        }
        userRepository.save(user);
        return user.getUserId();
    }
}
