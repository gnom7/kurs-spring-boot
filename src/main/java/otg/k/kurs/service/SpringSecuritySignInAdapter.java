package otg.k.kurs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;
import otg.k.kurs.domain.Role;
import otg.k.kurs.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class SpringSecuritySignInAdapter implements SignInAdapter {

    @Autowired
    private UserRepository userRepository;

    public String signIn(String localUserId, Connection<?> connection, NativeWebRequest request) {
        Set<GrantedAuthority> authorities = new HashSet<>(1);
        authorities.add(new SimpleGrantedAuthority(Role.ROLE_USER.name()));
        String password = userRepository.findByUsername(localUserId).getPassword();
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(localUserId, password, authorities));
        return null;
    }
}