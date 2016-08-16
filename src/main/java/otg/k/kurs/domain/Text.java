package otg.k.kurs.domain;

import lombok.Data;
import lombok.ToString;
import org.hibernate.search.annotations.Field;
import otg.k.kurs.dto.TextDto;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString(exclude = "site")
@Entity
@Table(name = "texts")
public class Text implements Serializable {

    @Id
    @GeneratedValue
    private long textId;

    @Field
    @Column(length = 100_000)
    private String markdownText;

    private int position;

    @ManyToOne
    @JoinColumn(name = "site_id")
    private Site site;

    public Text(){}

    public Text(String markdownText, int position) {
        this.markdownText = markdownText;
        this.position = position;
    }

    public Text(TextDto textDto, Site site) {
        this.markdownText = textDto.getMarkdownText();
        this.position = textDto.getPosition();
        this.site = site;
    }
}
