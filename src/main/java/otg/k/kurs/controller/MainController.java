package otg.k.kurs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.security.SocialAuthenticationProvider;
import org.springframework.social.security.SocialAuthenticationToken;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private ConnectionRepository repository;

    @Autowired
    private SocialAuthenticationProvider provider;

    @Autowired
    @Qualifier("socialDetails")
    private SocialUserDetailsService socialUserDetailsService;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @GetMapping("/index")
    public String index(Model model) {
        Connection connection = repository.getPrimaryConnection(Facebook.class);
        if(connection == null) return "auth/login";

//        SocialAuthenticationProvider authenticationProvider = new SocialAuthenticationProvider(usersConnectionRepository, socialUserDetailsService);
        SecurityContextHolder.getContext().setAuthentication(provider.authenticate(new SocialAuthenticationToken(connection, null)));
        return "index";
    }

}
