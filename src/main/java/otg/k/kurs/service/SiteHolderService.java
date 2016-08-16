package otg.k.kurs.service;

import otg.k.kurs.domain.SiteHolder;
import otg.k.kurs.domain.User;

import java.io.IOException;
import java.util.List;

public interface SiteHolderService {

    boolean saveSiteHolder(SiteHolder siteHolder);

    SiteHolder createSiteHolder(String siteHolderDtoJSON, User user) throws IOException;

    boolean isSiteHolderNameExist(String siteHolderName);

    SiteHolder getBySiteHolderName(String siteHolderName);

    void deleteSiteHolder(String siteHolderName);

    List<SiteHolder> getByUsername(String username);
}
