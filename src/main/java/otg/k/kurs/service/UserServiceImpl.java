package otg.k.kurs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otg.k.kurs.domain.Role;
import otg.k.kurs.domain.User;
import otg.k.kurs.domain.VerificationToken;
import otg.k.kurs.dto.UserDto;
import otg.k.kurs.event.OnRegistrationCompleteEvent;
import otg.k.kurs.repository.TokenRepository;
import otg.k.kurs.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public void registerSocialUser(UserProfile userProfile) {
        User user = createSocialUser(userProfile);
        userRepository.save(user);
    }

    private User createSocialUser(UserProfile userProfile){
        String password = passwordEncoder.encode(UUID.randomUUID().toString());
        return new User(userProfile.getUsername(), userProfile.getFirstName(),
                userProfile.getLastName(), userProfile.getEmail(), password, false, true, Role.ROLE_USER);
    }

    @Override
    public boolean registerUser(UserDto userDto, HttpServletRequest request) {
        User user = saveUser(userDto);
        if (user == null) {
            return false;
        }
        VerificationToken verToken = createVerificationToken(user);
        publishEvent(request, user, verToken.getToken());
        return true;
    }

    private boolean publishEvent(HttpServletRequest request, User user, String token){
        try {
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, token,
                    String.format("%s://%s:%d", request.getScheme(),
                            request.getServerName(), request.getServerPort()), request.getLocale()));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public VerificationToken createVerificationToken(User user) {
        return tokenRepository.save(new VerificationToken(user));
    }

    @Override
    public boolean activateUser(String token) {
        VerificationToken verificationToken = tokenRepository.findByToken(token);
        if (verificationToken == null) {
            return false;
        }
        User user = verificationToken.getUser();
        Calendar calendar = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - calendar.getTime().getTime()) <= 0) {
            return false;
        }
        user.setEnabled(true);
        userRepository.save(user);
        return true;
    }

    @Override
    public void resendConfirmationMessage(HttpServletRequest request, String token) {
        VerificationToken verToken = tokenRepository.findByToken(token);
        User user = verToken.getUser();
        verToken.refreshToken();
        tokenRepository.save(verToken);
        publishEvent(request, user, verToken.getToken());

    }

    private User saveUser(UserDto userDto) {
        User user = createUser(userDto);
        return (!userExist(userDto) && userRepository.save(user) != null) ? user : null;
    }

    private User createUser(UserDto userDto) {
        User user = new User();
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEnabled(false);
        user.setRole(Role.ROLE_USER);
        return user;
    }

    private boolean userExist(UserDto user) {
        return emailExist(user.getEmail()) || usernameExist(user.getUsername());
    }

    @Override
    public boolean emailExist(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public boolean usernameExist(String username) {
        return userRepository.findByUsername(username) != null;
    }

    @Override
    public User findUserByUsername(String username) {return userRepository.findByUsername(username);}
}
