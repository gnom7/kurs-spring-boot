package otg.k.kurs.dto;

import lombok.Data;
import otg.k.kurs.domain.Video;

@Data
public class VideoDto {

    private String url;

    private int width;

    private int height;

    private int position;

    public VideoDto() {
    }

    public VideoDto(Video video) {
        this.url = video.getUrl();
        this.width = video.getWidth();
        this.height = video.getHeight();
        this.position = video.getPosition();
    }
}
