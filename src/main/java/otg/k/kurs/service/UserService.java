package otg.k.kurs.service;

import org.springframework.social.connect.UserProfile;
import otg.k.kurs.domain.User;
import otg.k.kurs.domain.VerificationToken;
import otg.k.kurs.dto.UserDto;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    boolean registerUser(UserDto userDto, HttpServletRequest request);

    void registerSocialUser(UserProfile userProfile);

    VerificationToken createVerificationToken(User user);

    boolean activateUser(String token);

    void resendConfirmationMessage(HttpServletRequest request, String token);

    boolean emailExist(String email);

    boolean usernameExist(String username);

    User getUserByUsername(String username);

    User getCurrentUser();
}
