package otg.k.kurs.dto;

import lombok.Data;
import otg.k.kurs.domain.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class SiteDto {

    private String siteName;

    private String siteHolderName;

    private String theme;

    private int[][] grid;

    private boolean allowRating;

    private boolean allowComments;

    private List<ImageDto> images;

    private List<TextDto> texts;

    private List<VideoDto> videos;

    private String username;

    private String menu;

    private List<CommentDto> comments;

    public SiteDto(){}

    public SiteDto(Site site){
        this.siteName = site.getSiteName();
        this.siteHolderName = site.getSiteHolder().getSiteHolderName();
        this.menu = site.getMenu();
        this.theme = site.getTheme();
        this.grid = site.getGrid();
        this.allowRating = site.isAllowRating();
        this.allowComments = site.isAllowComments();
        this.username = site.getUser().getUsername();
        this.images = site.getImages().stream().map(ImageDto::new).collect(Collectors.toList());
        this.texts = site.getTexts().stream().map(TextDto::new).collect(Collectors.toList());
        this.videos = site.getVideos().stream().map(VideoDto::new).collect(Collectors.toList());
        this.comments = site.getComments().stream().map(CommentDto::new).collect(Collectors.toList());
    }
}
