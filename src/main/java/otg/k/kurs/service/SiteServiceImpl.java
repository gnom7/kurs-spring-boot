package otg.k.kurs.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otg.k.kurs.domain.Site;
import otg.k.kurs.repository.SiteRepository;

import java.io.IOException;
import java.util.Map;

@Service("siteService")
@Transactional
public class SiteServiceImpl implements SiteService{

    @Autowired
    private SiteRepository siteRepository;

    @Override
    public Site getSite(String user) {
        return null;
    }

    @Override
    public boolean isSiteNameExist(String siteName){
        return siteRepository.findBySiteName(siteName) != null;
    }

    @Override
    public Site createSite(String siteJSON) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(siteJSON, Site.class);
    }

    @Override
    public void saveSite(Site site){
        siteRepository.save(site);
    }
}
