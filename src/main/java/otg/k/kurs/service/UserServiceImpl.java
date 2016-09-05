package otg.k.kurs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otg.k.kurs.domain.ForgotPasswordToken;
import otg.k.kurs.domain.Role;
import otg.k.kurs.domain.User;
import otg.k.kurs.domain.VerificationToken;
import otg.k.kurs.dto.AccountDto;
import otg.k.kurs.dto.UserDto;
import otg.k.kurs.event.ForgotPasswordEvent;
import otg.k.kurs.event.OnRegistrationCompleteEvent;
import otg.k.kurs.repository.ForgotPasswordRepository;
import otg.k.kurs.repository.TokenRepository;
import otg.k.kurs.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private ForgotPasswordRepository forgotPasswordRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public User createSocialUser(Connection<?> connection){
        UserProfile userProfile = connection.fetchUserProfile();
        User user = new User(userProfile.getName(), userProfile.getFirstName(), userProfile.getLastName(),
                userProfile.getEmail(), passwordEncoder.encode(UUID.randomUUID().toString()), false, true, Role.ROLE_USER);
        user.setThirdPartyId(connection.createData().getProviderId() + connection.createData().getProviderUserId());
        return user;
    }

    @Override
    public boolean registerUser(AccountDto accountDto, HttpServletRequest request) {
        User user = saveUser(accountDto);
        if (user == null) {
            return false;
        }
        VerificationToken verToken = createVerificationToken(user);
        publishSendConfirmationTokenEvent(request, user, verToken.getToken());
        return true;
    }

    @Override
    public VerificationToken createVerificationToken(User user) {
        return tokenRepository.save(new VerificationToken(user));
    }

    @Override
    public boolean activateUser(String token) {
        if(token == null) return false;
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
    public void resendConfirmationMessage(String email, HttpServletRequest request) {
        User user = userRepository.findByEmail(email);
        VerificationToken verToken = createVerificationToken(user);
        publishSendConfirmationTokenEvent(request, user, verToken.getToken());
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
    public User getUserByUsername(String username) {return userRepository.findByUsername(username);}

    @Override
    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = null;
        if (principal instanceof User) {
            user = getUserByUsername(((User) principal).getUsername());
        } else {
            String username = principal.toString();
            user = getUserByUsername(username);
        }
        return user;
    }

    @Override
    public boolean saveUser(User user){return userRepository.save(user) != null;}

    @Override
    public boolean changePassword(User user, String oldPassword, String newPassword){
        if( passwordEncoder.matches(oldPassword, user.getPassword()) ){
            user.setPassword(passwordEncoder.encode(newPassword));
            return userRepository.save(user) != null;
        }
        return false;
    }

    @Override
    public void sendEmailToResetPassword(String email, HttpServletRequest request){
        User user = userRepository.findByEmail(email);
        String token = UUID.randomUUID().toString();
        Date expirationDate = new Date(Calendar.getInstance().getTime().getTime() + 5*60*1000);
        ForgotPasswordToken forgotPasswordToken = new ForgotPasswordToken(token, user, expirationDate);
        publishForgotPasswordEvent(request, forgotPasswordToken);
        forgotPasswordRepository.save(forgotPasswordToken);
    }

    @Override
    public boolean resetPassword(String newPassword, String token){
        ForgotPasswordToken forgotToken = forgotPasswordRepository.findByToken(token);
        if(forgotToken == null) {return false;}
        if( forgotToken.getExpirationDate().getTime() - Calendar.getInstance().getTime().getTime() < 0) {return false;}
        User user = forgotToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        return userRepository.save(user) != null;
    }

    private boolean publishForgotPasswordEvent(HttpServletRequest request, ForgotPasswordToken token){
        try {
            eventPublisher.publishEvent(new ForgotPasswordEvent(token,
                    String.format("%s://%s:%d", request.getScheme(),
                            request.getServerName(), request.getServerPort())));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private User saveUser(AccountDto accountDto) {
        User user = createUser(accountDto);
        return (!userExist(accountDto) && userRepository.save(user) != null) ? user : null;
    }

    private User createUser(AccountDto accountDto) {
        User user = new User();
        user.setFirstname(accountDto.getFirstname());
        user.setLastname(accountDto.getLastname());
        user.setUsername(accountDto.getUsername());
        user.setEmail(accountDto.getEmail());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEnabled(false);
        user.setRole(Role.ROLE_USER);
        user.setAvatarUrl("//placehold.it/500x300&text=%20");
        return user;
    }

    private boolean userExist(AccountDto user) {
        return emailExist(user.getEmail()) || usernameExist(user.getUsername());
    }

    private boolean publishSendConfirmationTokenEvent(HttpServletRequest request, User user, String token){
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
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(String username){
        userRepository.delete(username);
    }

    @Override
    public void updateUserFromDto(UserDto userDto, String password){
        User user = userRepository.findByUsername(userDto.getUsername());
        if(user == null){
            user = new User();
        }
        if( !"".equals(password)) {
            user.setPassword(passwordEncoder.encode(password));
        }
        user.setUsername(userDto.getUsername());
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setAvatarUrl(userDto.getAvatarUrl());
        user.setRole(userDto.getRole());
        user.setEnabled(userDto.isEnabled());
        user.setLocked(userDto.isLocked());
        userRepository.save(user);
    }
}
