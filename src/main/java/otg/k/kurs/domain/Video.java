package otg.k.kurs.domain;

import lombok.Data;
import lombok.ToString;
import otg.k.kurs.dto.VideoDto;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString(exclude = "site")
@Entity
@Table(name = "videos")
public class Video implements Serializable {

    @Id
    @GeneratedValue
    private long videoId;

    private String url;

    private int width;

    private int height;

    private int position;

    @ManyToOne
    @JoinColumn(name = "site_name")
    private Site site;

    public Video(){}

    public Video(String url, int width, int height, int position) {
        this.url = url;
        this.width = width;
        this.height = height;
        this.position = position;
    }

    public Video(VideoDto videoDto, Site site) {
        this.url = videoDto.getUrl();
        this.width = videoDto.getWidth();
        this.height = videoDto.getHeight();
        this.position = videoDto.getPosition();
        this.site = site;
    }
}
