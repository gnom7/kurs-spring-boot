package otg.k.kurs.domain;

import lombok.Data;
import org.hibernate.search.annotations.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.security.SocialUserDetails;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Indexed
@Table(name = "users")
public class User implements UserDetails, SocialUserDetails {

    @Id
    private String username;

    @Field
    private String firstname;

    @Field
    private String lastname;

    @Column(unique = true)
    private String email;

    @Column(length = 60)
    private String password;

    private boolean locked;

    private boolean enabled;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @IndexedEmbedded
    private List<Comment> comments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @IndexedEmbedded
    private List<SiteHolder> siteHolders;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @IndexedEmbedded
    private List<Site> sites;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Vote> votes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<VerificationToken> tokens;

    private String avatarUrl;

    private String thirdPartyId;

    public User(){}

    public User(String username){
        this.username = username;
        this.siteHolders = new ArrayList<>();
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


    @Override
    public String getUserId() {
        return username;
    }
}