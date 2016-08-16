package otg.k.kurs.dto;

import lombok.Data;
import otg.k.kurs.domain.SiteHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class SiteHolderDto {

    private String siteHolderName;

    private List<SiteDto> sites;

    private String username;

    public SiteHolderDto() {
    }

    public SiteHolderDto(String siteHolderName) {this.siteHolderName = siteHolderName;}

    public SiteHolderDto(SiteHolder siteHolder){
        this.siteHolderName = siteHolder.getSiteHolderName();
        this.username = siteHolder.getUser().getUsername();
        this.sites = new ArrayList<>(siteHolder.getSites().size());
        this.sites.addAll(siteHolder.getSites().stream().map(SiteDto::new).collect(Collectors.toList()));
    }
}
