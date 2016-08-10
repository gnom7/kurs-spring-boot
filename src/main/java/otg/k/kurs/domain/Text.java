package otg.k.kurs.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "texts")
public class Text implements Serializable {

    @Id
    @GeneratedValue
    private long textId;

    private String markdownText;

    private String siteName;

}
