package otg.k.kurs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import otg.k.kurs.domain.*;
import otg.k.kurs.dto.SiteDto;
import otg.k.kurs.service.SiteHolderService;
import otg.k.kurs.service.SiteService;
import otg.k.kurs.service.UserService;

import java.io.IOException;
import java.util.Map;

@Controller
public class ConstructorController {

    @Autowired
    private SiteService siteService;

    @Autowired
    private SiteHolderService siteHolderService;

    @Autowired
    private UserService userService;

    @Secured("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("constructor")
    public String get(){
        return "constructor/index";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/savesite")
    public @ResponseBody String saveSite(@RequestParam(name = "siteHolder") String siteHolderDtoJSON) throws IOException {
        SiteHolder siteHolder = siteHolderService.createSiteHolder(siteHolderDtoJSON, userService.getCurrentUser());
        setSite(siteHolder);
        siteHolderService.saveSiteHolder(siteHolder);
        return "index";
    }

//    @PostMapping("/checkSiteNameExist")
//    public @ResponseBody Boolean checkSiteName(@RequestParam String siteName){
//        return siteService.isSiteNameExist(siteName);
//    }

    @PostMapping("/checkSiteHolderNameExist")
    public @ResponseBody Boolean checkSiteHolderName(@RequestParam String siteHolderName){
        return siteHolderService.isSiteHolderNameExist(siteHolderName);
    }

    private void setSite(SiteHolder siteHolder){
        for(Site site : siteHolder.getSites()){
            for(Text text : site.getTexts()){
                text.setSite(site);
            }
            for(Image image : site.getImages()){
                image.setSite(site);
            }
            for(Video video : site.getVideos()){
                video.setSite(site);
            }
        }
    }

}