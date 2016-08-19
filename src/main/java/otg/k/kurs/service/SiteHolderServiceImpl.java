package otg.k.kurs.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otg.k.kurs.domain.Site;
import otg.k.kurs.domain.SiteHolder;
import otg.k.kurs.domain.User;
import otg.k.kurs.dto.SiteDto;
import otg.k.kurs.dto.SiteHolderDto;
import otg.k.kurs.repository.SiteHolderRepository;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class SiteHolderServiceImpl implements SiteHolderService {

    @Autowired
    private SiteHolderRepository siteHolderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private SiteService siteService;

    @Override
    public boolean saveSiteHolder(SiteHolder siteHolder){
        return siteHolderRepository.save(siteHolder) != null;
    }

    @Override
    public SiteHolder createSiteHolder(String siteHolderDtoJSON) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SiteHolderDto siteHolderDto = mapper.readValue(siteHolderDtoJSON, SiteHolderDto.class);
        SiteHolder siteHolder = new SiteHolder(siteHolderDto);
        SiteHolder oldSiteHolder = siteHolderRepository.findOne(siteHolder.getId());
        User user;
        if(oldSiteHolder == null) {
            user = userService.getCurrentUser();
        } else {
            user = oldSiteHolder.getUser();
        }
        for(SiteDto siteDto : siteHolderDto.getSites()){
            Site site = siteService.createSiteFromDto(siteDto);
            site.setSiteHolder(siteHolder);
            site.setUser(user);
            siteHolder.getSites().add(site);
        }
        siteHolder.setUser(user);
        return siteHolder;
    }

    @Override
    public boolean isSiteHolderNameExist(String siteHolderName) {
        return siteHolderRepository.findBySiteHolderName(siteHolderName) != null;
    }

    @Override
    public SiteHolder getBySiteHolderName(String siteHolderName){
        return siteHolderRepository.findBySiteHolderName(siteHolderName);
    }

    @Override
    public void deleteSiteHolder(long siteHolderId){
        if(siteHolderRepository.exists(siteHolderId)){
            siteHolderRepository.delete(siteHolderId);
        }
    }

    @Override
    public List<SiteHolder> getByUsername(String username){
        return siteHolderRepository.findByUser(userService.getUserByUsername(username));
    }

    @Override
    public List<SiteHolder> getAll(){
        return siteHolderRepository.findAll();
    }

    @Override
    public SiteHolder getBySiteHolderId(long id){
        return siteHolderRepository.findOne(id);
    }
}
