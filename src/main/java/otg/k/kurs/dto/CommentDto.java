package otg.k.kurs.dto;

import lombok.Data;
import otg.k.kurs.domain.Comment;

import java.util.Date;

@Data
public class CommentDto {

    private long id;

    private String username;

    private String userAvatarUrl;

    private String comment;

    private long siteId;

    private Date date;

    public CommentDto(){}

    public CommentDto(Comment comment){
        this.username = comment.getUser().getUsername();
        this.userAvatarUrl = comment.getUser().getAvatarUrl();
        this.comment = comment.getComment();
        this.date = comment.getDate();
        this.id = comment.getId();
        this.siteId = comment.getSite().getId();
    }

}
