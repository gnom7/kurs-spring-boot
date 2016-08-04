package otg.k.kurs.service;

import otg.k.kurs.domain.User;
import otg.k.kurs.domain.VerificationToken;
import otg.k.kurs.dto.UserDto;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    boolean registerUser(UserDto userDto, HttpServletRequest request);

    VerificationToken createVerificationToken(User user);

    boolean activateUser(String token);

    void resendConfirmationMessage(HttpServletRequest request, String token);

}
