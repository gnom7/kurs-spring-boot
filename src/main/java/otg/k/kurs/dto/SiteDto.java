package otg.k.kurs.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SiteDto {

    @NotNull
    private String siteName;

    private String theme;

    private String grid;

    private boolean allowRating;

    private boolean allowComments;

}
