package otg.k.kurs.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import otg.k.kurs.domain.*;
import otg.k.kurs.dto.SiteDto;
import otg.k.kurs.dto.SiteHolderDto;
import otg.k.kurs.service.SiteHolderService;
import otg.k.kurs.service.SiteService;
import otg.k.kurs.service.TagService;
import otg.k.kurs.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
public class ConstructorController {

    @Autowired
    private TagService tagService;

    @Autowired
    private SiteHolderService siteHolderService;

    @Autowired
    private UserService userService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("constructor")
    public String get(){
        return "constructor/index";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/savesite")
    public @ResponseBody String saveSite(@RequestParam(name = "siteHolder") String siteHolderDtoJSON,
                                         HttpServletRequest request) throws IOException {
        System.out.println(siteHolderDtoJSON);
        SiteHolder siteHolder = siteHolderService.createSiteHolder(siteHolderDtoJSON);
        System.out.println(siteHolder);
        if( request.getRemoteUser().equals(siteHolder.getUser().getUsername()) || request.isUserInRole("ROLE_ADMIN")){
            siteHolderService.saveSiteHolder(siteHolder);
            return "index";
        };
        return "error";
    }

    @PostMapping("/checkSiteHolderNameExist")
    public @ResponseBody Boolean
    checkSiteHolderName(@RequestParam String siteHolderName, @RequestParam long siteHolderId, HttpServletRequest request){
        if(request.isUserInRole("ROLE_ADMIN")){
            return false;
        }
        List<SiteHolder> siteHolders = siteHolderService.getByUsername(request.getRemoteUser());
        boolean isUserSite = false;
        boolean differentId = false;
        boolean siteHolderNameExist = siteHolderService.isSiteHolderNameExist(siteHolderName);
        for(SiteHolder siteHolder : siteHolders){
            if(siteHolder.getSiteHolderName().equals(siteHolderName)) {
                isUserSite = true;
                if(siteHolderId != siteHolder.getId()) differentId = true;
            }
        }
        if(siteHolderNameExist) {
            if(isUserSite && !differentId) return false;
            return true;
        }
        return false;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/redactSite")
    public String redactSite(@RequestParam long id, Model model) throws JsonProcessingException {
        SiteHolder siteHolder = siteHolderService.getBySiteHolderId(id);
        if(siteHolder == null) {
            model.addAttribute("error", new Exception("Such site doesn't exist"));
            model.addAttribute("siteHolderDto", new SiteHolderDto("error"));
            model.addAttribute("site", new SiteDto("error"));
            return "site/site";
        }
        SiteHolderDto siteHolderDto = new SiteHolderDto(siteHolder);
        model.addAttribute("siteHolderDto", siteHolderDto);
        model.addAttribute("allTags", tagService.getAllStringTags());
        return "constructor/index";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/deleteSite")
    public @ResponseBody void deleteSite(@RequestParam long siteHolderId, HttpServletRequest request){
        String siteOwner = siteHolderService.getBySiteHolderId(siteHolderId).getUser().getUsername();
        if(request.getRemoteUser().equals(siteOwner) || request.isUserInRole("ROLE_ADMIN")){
            siteHolderService.deleteSiteHolder(siteHolderId);
        }
    }

}