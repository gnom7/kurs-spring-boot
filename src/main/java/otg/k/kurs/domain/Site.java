package otg.k.kurs.domain;

import lombok.Data;
import otg.k.kurs.dto.SiteDto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "sites")
public class Site implements Serializable {

    @Id
    private String siteName;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    private int[][] grid;

    private String theme;

    private boolean allowRating;

    private boolean allowComments;

    @OneToMany(mappedBy = "site")
    private List<Image> images;

    @OneToMany(mappedBy = "site")
    private List<Text> texts;

    @OneToMany(mappedBy = "site")
    private List<Video> videos;

    public Site(){}

    public Site(SiteDto siteDto){
        this.allowComments = siteDto.isAllowComments();
        this.allowRating = siteDto.isAllowRating();
        this.grid = siteDto.getGrid();
        this.siteName = siteDto.getSiteName();
        this.theme = siteDto.getTheme();
        this.images = siteDto.getImages();
        this.videos = siteDto.getVideos();
        this.texts = siteDto.getTexts();
    }
}
