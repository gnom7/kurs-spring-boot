package otg.k.kurs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import otg.k.kurs.domain.Site;
import otg.k.kurs.domain.SiteHolder;
import otg.k.kurs.dto.SiteDto;
import otg.k.kurs.dto.SiteHolderDto;
import otg.k.kurs.service.SiteHolderService;
import otg.k.kurs.service.SiteService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class IndexController {

    @Autowired
    private SiteService siteService;

    @GetMapping(value = {"/index", "/"})
    public String get(Model model){
        List<Site> sites = siteService.getAll();
        Collections.sort(sites, (o1, o2) -> siteService.getRating(o1) - siteService.getRating(o2));
        int i = sites.size() < 10 ? sites.size() : 10;
        List<Site> subSites = new ArrayList<Site>(sites.subList(0, i));
        List<SiteDto> siteDtoList = subSites.stream().map(SiteDto::new).collect(Collectors.toList());
        model.addAttribute("sites", siteDtoList);
        return "index";
    }

}
