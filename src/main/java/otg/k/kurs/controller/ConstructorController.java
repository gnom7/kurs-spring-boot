package otg.k.kurs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import otg.k.kurs.domain.*;
import otg.k.kurs.dto.SiteDto;
import otg.k.kurs.service.SiteService;
import otg.k.kurs.service.UserService;

import java.io.IOException;
import java.util.Map;

@Controller
public class ConstructorController {

    @Autowired
    private SiteService siteService;

    @Autowired
    private UserService userService;

    @GetMapping("constructor")
    public String get(){
        return "constructor/index";
    }

    @PostMapping("/savesite")
    public @ResponseBody String saveSite(@RequestParam(name = "site") String siteDtoJSON) throws IOException {
        Site site = siteService.createSite(siteDtoJSON, userService.getCurrentUser());
        setSite(site);
        siteService.saveSite(site);
        return "index";
    }

    @PostMapping("/checkSiteNameExist")
    public @ResponseBody Boolean checkSiteName(@RequestParam String siteName){
        return siteService.isSiteNameExist(siteName);
    }

    private void setSite(Site site){
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