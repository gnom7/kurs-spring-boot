package otg.k.kurs.domain;


import lombok.Data;
import org.hibernate.search.annotations.Field;
import otg.k.kurs.dto.TagDto;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue
    private long id;

    @Field
    private String tag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "siteName")
    private Site site;

    public Tag(){}

    public Tag(TagDto tagDto, Site site){
        this.tag = tagDto.getTag();
        this.site = site;
    }

}
