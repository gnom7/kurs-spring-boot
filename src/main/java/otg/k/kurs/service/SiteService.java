package otg.k.kurs.service;

import otg.k.kurs.domain.Site;

import java.io.IOException;

public interface SiteService {

    Site getSite(String user);

    boolean isSiteNameExist(String siteName);

    Site createSite(String siteJSON) throws IOException;

    void saveSite(Site site);
}
