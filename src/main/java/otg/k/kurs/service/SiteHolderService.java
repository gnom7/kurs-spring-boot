package otg.k.kurs.service;

import otg.k.kurs.domain.SiteHolder;
import otg.k.kurs.domain.User;

import java.io.IOException;
import java.util.List;

public interface SiteHolderService {

    boolean saveSiteHolder(SiteHolder siteHolder);

    SiteHolder createSiteHolder(String siteHolderDtoJSON) throws IOException;

    boolean isSiteHolderNameExist(String siteHolderName);

    SiteHolder getBySiteHolderName(String siteHolderName);

    void deleteSiteHolder(long siteHolderId);

    List<SiteHolder> getByUsername(String username);

    List<SiteHolder> getAll();

    SiteHolder getBySiteHolderId(long id);
}
