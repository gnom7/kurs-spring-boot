package otg.k.kurs.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "videos")
public class Video implements Serializable {

    @Id
    @GeneratedValue
    private long videoId;

    private String url;

    private int width;

    private int height;

    private boolean autoplay;

    private boolean videoLoop;

    @ManyToOne
    @JoinColumn(name = "site_name")
    private Site site;

}
