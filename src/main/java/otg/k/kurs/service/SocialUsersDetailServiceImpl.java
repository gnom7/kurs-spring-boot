//package otg.k.kurs.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.social.security.SocialUser;
//import org.springframework.social.security.SocialUserDetails;
//import org.springframework.social.security.SocialUserDetailsService;
//import org.springframework.stereotype.Service;
//
//@Service
//public class SocialUsersDetailServiceImpl implements SocialUserDetailsService {
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Override
//    public SocialUserDetails loadUserByUserId(String s) throws UsernameNotFoundException {
//        UserDetails userDetails = userDetailsService.loadUserByUsername(s);
//        return new SocialUser(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
//    }
//
//}
