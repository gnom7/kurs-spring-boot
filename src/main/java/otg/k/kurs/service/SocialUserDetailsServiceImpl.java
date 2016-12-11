package otg.k.kurs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;
import otg.k.kurs.repository.UserRepository;

import javax.annotation.PostConstruct;

@Service
public class SocialUserDetailsServiceImpl implements SocialUserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @Autowired
    private ConnectionSignUp connectionSignUp;

    @PostConstruct
    public void init() {
        ((InMemoryUsersConnectionRepository)usersConnectionRepository).setConnectionSignUp(connectionSignUp);
    }

    @Override
    public SocialUserDetails loadUserByUserId(String localUserId) throws UsernameNotFoundException {
        UserDetails userDetails = userRepository.findByUsername(localUserId);
        return new SocialUser(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
    }

}
