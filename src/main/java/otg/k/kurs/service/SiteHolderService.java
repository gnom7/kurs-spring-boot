package otg.k.kurs.service;

import otg.k.kurs.domain.SiteHolder;
import otg.k.kurs.domain.User;

import java.io.IOException;

public interface SiteHolderService {

    boolean saveSiteHolder(SiteHolder siteHolder);

    SiteHolder createSiteHolder(String siteHolderDtoJSON, User user) throws IOException;

    boolean isSiteHolderNameExist(String siteHolderName);
}
