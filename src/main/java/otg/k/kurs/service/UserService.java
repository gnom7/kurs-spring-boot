package otg.k.kurs.service;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import otg.k.kurs.domain.User;
import otg.k.kurs.domain.VerificationToken;
import otg.k.kurs.dto.AccountDto;
import otg.k.kurs.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    User createSocialUser(Connection<?> connection);

    boolean registerUser(AccountDto accountDto, HttpServletRequest request);

    VerificationToken createVerificationToken(User user);

    boolean activateUser(String token);

    void resendConfirmationMessage(String email, HttpServletRequest request);

    boolean emailExist(String email);

    boolean usernameExist(String username);

    User getUserByUsername(String username);

    User getCurrentUser();

    boolean saveUser(User user);

    boolean changePassword(User user, String oldPassword, String newPassword);

    void sendEmailToResetPassword(String email, HttpServletRequest request);

    boolean resetPassword(String newPassword, String token);

    List<User> getAll();

    void deleteUser(String username);

    void updateUserFromDto(UserDto userDto, String password);
}
