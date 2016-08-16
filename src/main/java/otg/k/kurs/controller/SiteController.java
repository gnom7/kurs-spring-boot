package otg.k.kurs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import otg.k.kurs.domain.*;
import otg.k.kurs.dto.CommentDto;
import otg.k.kurs.dto.SiteDto;
import otg.k.kurs.dto.SiteHolderDto;
import otg.k.kurs.service.CommentService;
import otg.k.kurs.service.SiteHolderService;
import otg.k.kurs.service.SiteService;
import otg.k.kurs.service.UserService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class SiteController {

    @Autowired
    private SiteService siteService;

    @Autowired
    private SiteHolderService siteHolderService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/{username}/{siteHolderName}/{siteName}")
    public String getSite(@PathVariable String username, @PathVariable String siteHolderName,
                          @PathVariable String siteName, Model model){
        SiteHolder siteHolder = siteHolderService.getBySiteHolderName(siteHolderName);
        Site site = findSite(siteHolder, siteName);
        if(siteHolder != null &&  site != null) {
            SiteHolderDto siteHolderDto = new SiteHolderDto(siteHolder);
            model.addAttribute("siteHolderDto", siteHolderDto);
            model.addAttribute("site", new SiteDto(site));
        } else {
            model.addAttribute("error", new Exception("Such site doesn't exist"));
            model.addAttribute("siteHolderDto", new SiteHolderDto("error"));
            model.addAttribute("site", new SiteDto("error"));
        }
        return "site/site";
    }

    @PostMapping("/addComment")
    public @ResponseBody void addComment(@RequestParam String comment, @RequestParam long siteId){
        Comment c = new Comment();
        c.setComment(comment);
        c.setDate(Calendar.getInstance().getTime());
        c.setUser(userService.getCurrentUser());
        c.setSite(new Site(siteId));
        commentService.addComment(c);
    }

    private Site findSite(SiteHolder siteHolder, String siteName){
        Site site = null;
        System.out.println(siteHolder);
        for(Site s : siteHolder.getSites()){
            if(siteName.equals(s.getSiteName())){
                site = s;
            }
        }
        return site;
    }

}
