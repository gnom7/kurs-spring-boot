package otg.k.kurs.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "sites")
public class Site implements Serializable {

    @Id
    @GeneratedValue
    private Long siteId;

    private String siteName;

    private String username;

    private String grid;

    private String theme;

//    private List<Image> images;
//
//    private List<Text> texts;
//
//    private List<Video> videos;

}
