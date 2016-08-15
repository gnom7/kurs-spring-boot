package otg.k.kurs.domain;


import lombok.Data;
import org.hibernate.search.annotations.Field;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tags")
public class Tag {

    @Id
    private long id;

    @Field
    private String tag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "siteName")
    private Site site;

}
