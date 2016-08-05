//package otg.k.kurs.controller.interceptors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.social.UserIdSource;
//import org.springframework.social.connect.*;
//import org.springframework.social.connect.web.*;
//import org.springframework.social.security.SocialAuthenticationFilter;
//import org.springframework.social.security.SocialAuthenticationProvider;
//import org.springframework.social.security.SocialAuthenticationServiceLocator;
//import org.springframework.social.security.SocialAuthenticationToken;
//import org.springframework.social.twitter.api.Twitter;
//import org.springframework.stereotype.Component;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.context.request.WebRequest;
//import otg.k.kurs.service.UserService;
//
//@Component
//public class TwitterAfterConnectInterceptor implements ConnectInterceptor<Twitter> {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private SocialAuthenticationProvider authenticationProvider;
//
//
//
//    @Override
//    public void preConnect(ConnectionFactory<Twitter> connectionFactory,
//                           MultiValueMap<String, String> multiValueMap, WebRequest webRequest) {
//
//    }
//
//    @Override
//    public void postConnect(Connection<Twitter> connection, WebRequest webRequest) {
//        UserProfile userProfile = connection.fetchUserProfile();
//        if( ! (userService.emailExist(userProfile.getEmail()) || userService.usernameExist(userProfile.getUsername())) ) {
//            userService.registerSocialUser(userProfile);
//        }
//        Authentication auth = authenticationProvider.authenticate(new SocialAuthenticationToken(connection, null));
//        SecurityContextHolder.getContext().setAuthentication(auth);
//    }
//}
