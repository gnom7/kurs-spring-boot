package otg.k.kurs.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;
import otg.k.kurs.domain.Role;
import otg.k.kurs.domain.User;
import otg.k.kurs.repository.UserRepository;

import java.util.UUID;

@Component
public class SimpleConnectionSignUp implements ConnectionSignUp {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String execute(Connection<?> connection) {
        User user = new User(connection.getDisplayName(),
                passwordEncoder.encode(UUID.randomUUID().toString()), Role.ROLE_USER);
        userRepository.save(user);
        return user.getUsername();
    }
}