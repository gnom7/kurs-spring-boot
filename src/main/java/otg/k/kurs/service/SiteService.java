package otg.k.kurs.service;

import otg.k.kurs.domain.Site;

public interface SiteService {

    Site getSite(String user);

    boolean isSiteNameExist(String siteName);
}
