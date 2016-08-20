package otg.k.kurs.dto;

import lombok.Data;
import otg.k.kurs.domain.Comment;
import otg.k.kurs.domain.Role;
import otg.k.kurs.domain.Site;
import otg.k.kurs.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDto {

    private String username;

    private String firstname;

    private String lastname;

    private String email;

    private boolean locked;

    private boolean enabled;

    private Role role;

    private List<SiteHolderDto> siteHolderDtoList;

    private List<CommentDto> comments;

    private List<VoteDto> votes;

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
        this.siteHolderDtoList = user.getSiteHolders().stream().map(SiteHolderDto::new).collect(Collectors.toList());
        this.comments = user.getComments().stream().map(CommentDto::new).collect(Collectors.toList());
        this.avatarUrl = user.getAvatarUrl();
    }
}
