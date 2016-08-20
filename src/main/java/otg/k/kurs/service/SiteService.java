package otg.k.kurs.service;

import otg.k.kurs.domain.Site;
import otg.k.kurs.domain.User;
import otg.k.kurs.dto.SiteDto;

import java.io.IOException;
import java.util.List;

public interface SiteService {

    List<Site> findByUser(String username);

    boolean isSiteNameExist(String siteName);

    List<Site> findBySiteName(String siteName);

    Site createSiteFromDtoJson(String siteDtoJSON, User user) throws IOException;

    void saveSite(Site site);

    Site createSiteFromDto(SiteDto siteDto);

    List<Site> getAll();

    int getRating(Site site);
}
