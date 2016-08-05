//package otg.k.kurs.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.social.connect.Connection;
//import org.springframework.social.connect.ConnectionSignUp;
//import org.springframework.social.connect.UserProfile;
//import org.springframework.stereotype.Component;
//import otg.k.kurs.domain.Role;
//import otg.k.kurs.domain.User;
//import otg.k.kurs.repository.UserRepository;
//
//import java.util.UUID;
//
//@Component
//public class UserConnectionSignUp implements ConnectionSignUp {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    public String execute(Connection<?> connection) {
//        try {
//            UserProfile profile = connection.fetchUserProfile();
//            User user = new User(profile.getUsername(), profile.getFirstName(), profile.getLastName(),
//                    profile.getEmail(), UUID.randomUUID().toString(), false, true, Role.ROLE_USER);
//            userRepository.save(user);
//            return user.getUsername();
//        } catch (Exception e){return null;}
//    }
//
//}
