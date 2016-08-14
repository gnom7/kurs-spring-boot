package otg.k.kurs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import otg.k.kurs.domain.*;
import otg.k.kurs.dto.CommentDto;
import otg.k.kurs.dto.SiteDto;
import otg.k.kurs.service.CommentService;
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
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/{username}/sites/{siteName}")
    public String getSite(@PathVariable String username, @PathVariable String siteName, Model model){
        List<Site> sites = siteService.findByUser(username);
        Site site = null;
        for(Site s : sites){
            if(s.getSiteName().equals(siteName)) site = s;
        };
        if(site != null) {
            SiteDto siteDto = new SiteDto(site);
            removeCircularReferences(siteDto);
            model.addAttribute("site", siteDto);
        } else {
            model.addAttribute("error", new Exception("Such site doesn't exist"));
        }
        return "site/site";
    }

    @PostMapping("/addComment")
    public @ResponseBody void addComment(@RequestParam String comment, @RequestParam String siteName){
        Comment c = new Comment();
        c.setComment(comment);
        c.setDate(Calendar.getInstance().getTime());
        c.setUser(userService.getCurrentUser());
        c.setSite(new Site(siteName));
        commentService.addComment(c);
    }

    private void removeCircularReferences(SiteDto site){
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
