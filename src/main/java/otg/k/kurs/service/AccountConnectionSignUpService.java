//package otg.k.kurs.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.social.connect.Connection;
//import org.springframework.social.connect.ConnectionSignUp;
//import org.springframework.social.connect.UserProfile;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import otg.k.kurs.domain.User;
//import otg.k.kurs.repository.UserRepository;
//
//import java.util.UUID;
//
//@Service
//public class AccountConnectionSignUpService implements ConnectionSignUp {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public String execute(Connection<?> connection) {
//        UserProfile profile = connection.fetchUserProfile();
//        String password = passwordEncoder.encode(UUID.randomUUID().toString());
//        User user = new User(profile.getUsername(), profile.getFirstName(),
//                profile.getLastName(), profile.getEmail(), password, false, true);
//        userRepository.save(user);
//        return profile.getUsername();
//    }
//
//}
