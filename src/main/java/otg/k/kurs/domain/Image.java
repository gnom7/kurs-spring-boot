package otg.k.kurs.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue
    private long imageId;

    private String url;

//    private Site site;

}
