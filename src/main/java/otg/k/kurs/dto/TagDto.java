package otg.k.kurs.dto;

import lombok.Data;
import otg.k.kurs.domain.Tag;

@Data
public class TagDto {

    private String tag;

    public TagDto() {
    }

    public TagDto(Tag tag) {
        this.tag = tag.getTag();
    }
}
