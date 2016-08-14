package otg.k.kurs.domain;

import lombok.Data;
import lombok.ToString;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import otg.k.kurs.dto.SiteDto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@ToString(exclude = "user")
@Entity
@Indexed
@Table(name = "sites")
public class Site implements Serializable {

    @Id
    private String siteName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username")
    @IndexedEmbedded(includeEmbeddedObjectId = true)
    private User user;

    private int[][] grid;

    @Field
    private String theme;

    private boolean allowRating;

    private boolean allowComments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "site")
    private List<Image> images;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "site")
    @IndexedEmbedded
    private List<Text> texts;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "site")
    private List<Video> videos;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "site")
    @IndexedEmbedded
    private List<Comment> comments;

    public Site(){}

    public Site(String siteName){
        this.siteName = siteName;
    }

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
