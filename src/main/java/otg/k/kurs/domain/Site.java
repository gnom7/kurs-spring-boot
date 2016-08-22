package otg.k.kurs.domain;

import lombok.Data;
import lombok.ToString;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "siteHolderId")
    @IndexedEmbedded(depth = 1)
    private SiteHolder siteHolder;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "tag_site", joinColumns = {
            @JoinColumn(name = "id")},
            inverseJoinColumns = {@JoinColumn(name = "tagId")})
    @IndexedEmbedded(depth = 1)
    private List<Tag> tags;

    private int[][] grid;

    @Field
    private String theme;

    private String menu;

    private boolean allowRating;

    private boolean allowComments;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "site")
    private List<Image> images;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "site")
    @IndexedEmbedded
    private List<Text> texts;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "site")
    private List<Video> videos;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "site")
    private List<TableChart> tables;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "site")
    private List<LineChart> lines;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "site")
    @IndexedEmbedded
    private List<Comment> comments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "site")
    private List<Vote> votes;

    private String logoUrl;

    public Site(){}

    public Site(long siteId){
        this.id = siteId;
    }

    public Site(String siteName){
        this.siteName = siteName;
    }

    public Site(SiteDto siteDto){
        this.allowComments = siteDto.isAllowComments();
        this.allowRating = siteDto.isAllowRating();
        this.grid = siteDto.getGrid();
        this.siteName = siteDto.getSiteName();
        this.id = siteDto.getId();
        this.menu = siteDto.getMenu();
        this.theme = siteDto.getTheme();
        this.images = new ArrayList<>(siteDto.getImages().size());
        this.videos = new ArrayList<>(siteDto.getVideos().size());
        this.texts = new ArrayList<>(siteDto.getTexts().size());
        this.tables = new ArrayList<>(siteDto.getTables().size());
        this.lines = new ArrayList<>(siteDto.getLines().size());
        this.tags = new ArrayList<>();
        this.images.addAll(siteDto.getImages().stream().map(imageDto -> new Image(imageDto, this)).collect(Collectors.toList()));
        this.videos.addAll(siteDto.getVideos().stream().map(videoDto -> new Video(videoDto, this)).collect(Collectors.toList()));
        this.texts.addAll(siteDto.getTexts().stream().map(textDto -> new Text(textDto, this)).collect(Collectors.toList()));
        this.tables.addAll(siteDto.getTables().stream().map(table -> new TableChart(table, this)).collect(Collectors.toList()));
        this.lines.addAll(siteDto.getLines().stream().map(line -> new LineChart(line, this)).collect(Collectors.toList()));
        String defaultUrl = "//placehold.it/500x300&text=%20";
        this.logoUrl = "".equals(siteDto.getLogoUrl()) ? defaultUrl : siteDto.getLogoUrl();
    }


}
