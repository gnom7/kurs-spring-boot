package otg.k.kurs.service;

import otg.k.kurs.domain.Site;
import otg.k.kurs.domain.User;

import java.io.IOException;
import java.util.List;

public interface SiteService {

    List<Site> findByUser(String username);

    boolean isSiteNameExist(String siteName);

    List<Site> findBySiteName(String siteName);

    Site createSite(String siteDtoJSON, User user) throws IOException;

    void saveSite(Site site);
}
