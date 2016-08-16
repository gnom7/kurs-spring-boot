package otg.k.kurs.dto;

import lombok.Data;
import otg.k.kurs.domain.Image;

@Data
public class ImageDto {

    private long id;

    private String url;

    private int position;

    public ImageDto() {
    }

    public ImageDto(Image image) {
        this.url = image.getUrl();
        this.position = image.getPosition();
        this.id = image.getImageId();
    }
}
