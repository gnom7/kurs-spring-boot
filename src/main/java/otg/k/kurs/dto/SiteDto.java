package otg.k.kurs.dto;

import lombok.Data;
import otg.k.kurs.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class SiteDto {

    private String siteName;

    private String theme;

    private int[][] grid;

    private boolean allowRating;

    private boolean allowComments;

    private List<Image> images;

    private List<Text> texts;

    private List<Video> videos;

    private String username;

    private List<CommentDto> comments;

    public SiteDto(){}

    public SiteDto(String siteName, String theme, int[][] grid, boolean allowRating,
                   boolean allowComments, List<Image> images, List<Text> texts, List<Video> videos) {
        this.siteName = siteName;
        this.theme = theme;
        this.grid = grid;
        this.allowRating = allowRating;
        this.allowComments = allowComments;
        this.images = images;
        this.texts = texts;
        this.videos = videos;
    }

    public SiteDto(Site site){
        this.siteName = site.getSiteName();
        this.theme = site.getTheme();
        this.grid = site.getGrid();
        this.allowRating = site.isAllowRating();
        this.allowComments = site.isAllowComments();
        this.images = site.getImages();
        this.texts = site.getTexts();
        this.videos = site.getVideos();
        this.username = site.getUser().getUsername();
        this.comments = site.getComments().stream().map(CommentDto::new).collect(Collectors.toList());
    }
}
