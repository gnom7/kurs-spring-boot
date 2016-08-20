package otg.k.kurs.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otg.k.kurs.domain.Site;
import otg.k.kurs.domain.Tag;
import otg.k.kurs.domain.User;
import otg.k.kurs.domain.Vote;
import otg.k.kurs.dto.SiteDto;
import otg.k.kurs.repository.SiteRepository;
import otg.k.kurs.repository.TagRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("siteService")
@Transactional
public class SiteServiceImpl implements SiteService{

    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private TagService tagService;

    @Autowired
    private UserService userService;

    @Override
    public boolean isSiteNameExist(String siteName){
        return siteRepository.findBySiteName(siteName) != null;
    }

    @Override
    public Site createSiteFromDtoJson(String siteDtoJSON, User user) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SiteDto siteDto = mapper.readValue(siteDtoJSON, SiteDto.class);
        Site site = createSiteFromDto(siteDto);
        site.setUser(user);
        return site;
    }

    @Override
    public void saveSite(Site site){
        siteRepository.save(site);
    }

    @Override
    public List<Site> findBySiteName(String siteName){
        return siteRepository.findBySiteName(siteName);
    }

    @Override
    public List<Site> findByUser(String username){
        return siteRepository.findByUser(userService.getUserByUsername(username));
    }

    @Override
    public Site createSiteFromDto(SiteDto siteDto){
        Site site = new Site(siteDto);
        List<Tag> tags = new ArrayList<>();
        for(String stringTag : siteDto.getTags()){
            Tag tag = tagService.getTagFromString(stringTag);
            tag.getSites().add(site);
            tags.add(tag);
        }
        site.setTags(tags);
        return site;
    }

    @Override
    public List<Site> getAll(){
        return siteRepository.findAll();
    }

    @Override
    public int getRating(Site site) {
        int rating = 0;
        for(Vote vote : site.getVotes()){
            rating += vote.getVote();
        }
        return rating;
    }

}
