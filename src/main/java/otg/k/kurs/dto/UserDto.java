package otg.k.kurs.dto;

import lombok.Data;
import otg.k.kurs.domain.Comment;
import otg.k.kurs.domain.Role;
import otg.k.kurs.domain.Site;
import otg.k.kurs.domain.User;

import java.util.List;

@Data
public class UserDto {

    private String username;

    private String firstname;

    private String lastname;

    private String email;

    private boolean locked;

    private boolean enabled;

    private Role role;

    private List<Site> sites;

    private List<Comment> comments;

    private String avatarUrl;

    public UserDto(){}

    public UserDto(User user) {
        this.username = user.getUsername();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
        this.locked = user.isLocked();
        this.enabled = user.isEnabled();
        this.role = user.getRole();
        this.sites = user.getSites();
        this.comments = user.getComments();
        this.avatarUrl = user.getAvatarUrl();
    }
}
