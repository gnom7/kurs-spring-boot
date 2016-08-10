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
    private String siteName;

    private String username;

    private String grid;

    private String theme;

    private boolean rating;

    private boolean comments;

    @OneToMany(mappedBy = "site")
    private List<Image> images;

    @OneToMany(mappedBy = "site")
    private List<Text> texts;

    @OneToMany(mappedBy = "site")
    private List<Video> videos;

}
