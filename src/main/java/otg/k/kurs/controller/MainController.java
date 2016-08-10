package otg.k.kurs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import otg.k.kurs.dto.SiteDto;
import otg.k.kurs.service.SiteService;

@Controller
public class MainController {

    @Autowired
    private SiteService siteService;

    @GetMapping("constructor")
    public String get(){
        return "constructor/index";
    }

    @PostMapping("/savesite")
    public String saveSite(@ModelAttribute("site") SiteDto site){
        System.out.println(site.getSiteName());
        System.out.println(site.getGrid());
        System.out.println(site.getTheme());
        System.out.println(site.isAllowComments());
        System.out.println(site.isAllowRating());
        return "index";
    }

    @PostMapping("/checkSiteName")
    public @ResponseBody String checkSiteName(@RequestParam String siteName){
        System.out.println("checking");
        if(siteService.isSiteNameExist(siteName)) return "exist";
        return "";
    }

}