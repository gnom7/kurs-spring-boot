package otg.k.kurs.domain;

import lombok.Data;
import lombok.ToString;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    private String username;

    private String firstname;

    private String lastname;

    @Column(unique = true)
    private String email;

    private String password;

    private boolean locked;

    private boolean enabled;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Site> sites;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    private String avatarUrl;

    public User(){}

    public User(String username, String firstname, String lastname){
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public User(String username, String firstname, String lastname, String email) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public User(String username, String firstname, String lastname, String email,
                String password, boolean locked, boolean enabled, Role role) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.locked = locked;
        this.enabled = enabled;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>(1);
        authorities.add(new SimpleGrantedAuthority(role.name()));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


}