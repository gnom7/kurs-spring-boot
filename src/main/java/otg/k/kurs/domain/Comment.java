package otg.k.kurs.domain;

import lombok.Data;
import lombok.ToString;
import org.hibernate.search.annotations.Field;
import otg.k.kurs.dto.CommentDto;

import javax.persistence.*;
import java.util.Date;

@Data
@ToString(exclude = {"user", "site"})
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    @ManyToOne
    @JoinColumn(name = "site_id")
    private Site site;

    @Field
    private String comment;

    private Date date;

    public Comment(){}

    public Comment(CommentDto commentDto) {
        this.user = new User(commentDto.getUsername()); // not doing DB query
        this.site = new Site(commentDto.getSiteId());
        this.comment = commentDto.getComment();
        this.date = commentDto.getDate();
    }
}
