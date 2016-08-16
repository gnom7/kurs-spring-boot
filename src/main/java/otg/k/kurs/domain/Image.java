package otg.k.kurs.domain;

import lombok.Data;
import lombok.ToString;
import otg.k.kurs.dto.ImageDto;

import javax.persistence.*;

@Data
@ToString(exclude = "site")
@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue
    private long imageId;

    private String url;

    private int position;

    @ManyToOne
    @JoinColumn(name = "site_name")
    private Site site;

    public Image(){}

    public Image(String url, int position) {
        this.url = url;
        this.position = position;
    }

    public Image(ImageDto imageDto, Site site) {
        this.url = imageDto.getUrl();
        this.position = imageDto.getPosition();
        this.site = site;
    }
}
