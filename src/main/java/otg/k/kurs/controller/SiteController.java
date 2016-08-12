package otg.k.kurs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import otg.k.kurs.domain.Image;
import otg.k.kurs.domain.Site;
import otg.k.kurs.domain.Text;
import otg.k.kurs.domain.Video;
import otg.k.kurs.dto.SiteDto;
import otg.k.kurs.service.SiteService;

import java.util.List;

@Controller
public class SiteController {

    @Autowired
    private SiteService siteService;

    @GetMapping("/{username}/site/{siteName}")
    public String getSite(@PathVariable String username, @PathVariable String siteName, Model model){
        List<Site> sites = siteService.findByUser(username);
        Site site = null;
        for(Site s : sites){
            if(s.getSiteName().equals(siteName)) site = s;
        };
        if(site != null) {
            SiteDto siteDto = new SiteDto(site);
            removeSiteReferences(siteDto);
            model.addAttribute("site", siteDto);
        } else {
            model.addAttribute("error", new Exception("Such site doesn't exist"));
        }
        return "site/site";
    }

    private void removeSiteReferences(SiteDto site){
        for(Image image : site.getImages()){
            image.setSite(null);
        }
        for(Text text : site.getTexts()){
            text.setSite(null);
        }
        for(Video video : site.getVideos()){
            video.setSite(null);
        }
    }

}
