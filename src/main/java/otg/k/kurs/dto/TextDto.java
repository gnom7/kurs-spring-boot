package otg.k.kurs.dto;

import lombok.Data;
import otg.k.kurs.domain.Text;

@Data
public class TextDto {

    private long id;

    private String markdownText;

    private int position;

    public TextDto() {
    }

    public TextDto(Text text) {
        this.markdownText = text.getMarkdownText();
        this.position = text.getPosition();
        this.id = text.getTextId();
    }
}
