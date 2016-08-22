package otg.k.kurs.domain;


import lombok.Data;
import lombok.ToString;
import org.hibernate.search.annotations.Field;
import otg.k.kurs.dto.TagDto;

import javax.persistence.*;
import java.util.List;

@Data
@ToString(exclude = "sites")
@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue
    private long tagId;

    @Field
    @Column(unique = true)
    private String tag;

    @ManyToMany(mappedBy = "tags")
    private List<Site> sites;

    public Tag(){}

}
