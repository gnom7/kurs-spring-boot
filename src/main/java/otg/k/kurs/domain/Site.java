package otg.k.kurs.domain;

import lombok.Data;
import lombok.ToString;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import otg.k.kurs.dto.ImageDto;
import otg.k.kurs.dto.SiteDto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@ToString(exclude = {"user", "siteHolder"})
@Entity
@Indexed
@Table(name = "sites")
public class Site implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Field
    private String siteName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username")
    @IndexedEmbedded(includeEmbeddedObjectId = true, depth = 1)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "siteHolderName")
    @IndexedEmbedded(includeEmbeddedObjectId = true, depth = 1)
    private SiteHolder siteHolder;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "site")
    @IndexedEmbedded(depth = 1)
    private List<Tag> tags;

    private int[][] grid;

    @Field
    private String theme;

    private String menu;

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
        this.menu = siteDto.getMenu();
        this.theme = siteDto.getTheme();
        this.images = new ArrayList<>(siteDto.getImages().size());
        this.videos = new ArrayList<>(siteDto.getVideos().size());
        this.texts = new ArrayList<>(siteDto.getTexts().size());
        this.images.addAll(siteDto.getImages().stream().map(imageDto -> new Image(imageDto, this)).collect(Collectors.toList()));
        this.videos.addAll(siteDto.getVideos().stream().map(videoDto -> new Video(videoDto, this)).collect(Collectors.toList()));
        this.texts.addAll(siteDto.getTexts().stream().map(textDto -> new Text(textDto, this)).collect(Collectors.toList()));
    }


}
