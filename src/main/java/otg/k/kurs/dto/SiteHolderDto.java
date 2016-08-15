package otg.k.kurs.dto;

import lombok.Data;
import otg.k.kurs.domain.Site;
import otg.k.kurs.domain.SiteHolder;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class SiteHolderDto {

    private String siteHolderName;

    private Set<SiteDto> sites;

    private String username;

    public SiteHolderDto() {
    }

    public SiteHolderDto(SiteHolder siteHolder){
        this.siteHolderName = siteHolder.getSiteHolderName();
        this.username = siteHolder.getUser().getUsername();
        Set<SiteDto> sites = new HashSet<>(siteHolder.getSites().size());
        sites.addAll(siteHolder.getSites().stream().map(SiteDto::new).collect(Collectors.toList()));
        this.sites = sites;
    }
}
