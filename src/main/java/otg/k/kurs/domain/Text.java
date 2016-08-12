package otg.k.kurs.domain;

import lombok.Data;
import org.hibernate.search.annotations.Field;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "texts")
public class Text implements Serializable {

    @Id
    @GeneratedValue
    private long textId;

    @Field
    private String markdownText;

    private int position;

    @ManyToOne
    @JoinColumn(name = "site_name")
    private Site site;

    public Text(){}

    public Text(String markdownText, int position) {
        this.markdownText = markdownText;
        this.position = position;
    }
}
