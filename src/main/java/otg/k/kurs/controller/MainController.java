package otg.k.kurs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import otg.k.kurs.dto.SiteDto;
import otg.k.kurs.service.SiteService;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private SiteService siteService;

    @GetMapping("constructor")
    public String get(){
        return "constructor/index";
    }

    @PostMapping("/savesite")
    public String saveSite(@RequestParam("site") String siteJSON){
        JacksonJsonParser parser = new JacksonJsonParser();
        Map map = parser.parseMap(siteJSON);
        System.out.println(map.get("siteName"));
        System.out.println(map.get("grid"));
        System.out.println(map.get("theme"));
        System.out.println(map.get("images"));
        System.out.println(map.get("videos"));
        System.out.println(map.get("texts"));
        System.out.println(map.get("videos"));
        System.out.println(map.get("allowRating"));
        System.out.println(map.get("allowComments"));
        return "index";
    }

    @PostMapping("/checkSiteNameExist")
    public @ResponseBody Boolean checkSiteName(@RequestParam String siteName){
        return siteService.isSiteNameExist(siteName);
    }

}