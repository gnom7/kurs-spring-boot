package otg.k.kurs.service;

import org.springframework.social.connect.UserProfile;
import otg.k.kurs.domain.User;
import otg.k.kurs.domain.VerificationToken;
import otg.k.kurs.dto.AccountDto;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    boolean registerUser(AccountDto accountDto, HttpServletRequest request);

    void registerSocialUser(UserProfile userProfile);

    VerificationToken createVerificationToken(User user);

    boolean activateUser(String token);

    void resendConfirmationMessage(String email, HttpServletRequest request);

    boolean emailExist(String email);

    boolean usernameExist(String username);

    User getUserByUsername(String username);

    User getCurrentUser();

    boolean saveUser(User user);

    boolean changePassword(String oldPassword, String newPassword);

    void sendEmailToResetPassword(String email, HttpServletRequest request);
}
