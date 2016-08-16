package otg.k.kurs.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import otg.k.kurs.domain.*;
import otg.k.kurs.dto.SiteHolderDto;
import otg.k.kurs.service.SiteHolderService;
import otg.k.kurs.service.SiteService;
import otg.k.kurs.service.UserService;

import java.io.IOException;
import java.util.List;

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
        System.out.println(siteHolderDtoJSON);
        SiteHolder siteHolder = siteHolderService.createSiteHolder(siteHolderDtoJSON, userService.getCurrentUser());
//        siteHolderService.deleteSiteHolder(siteHolder.getId());
        siteHolderService.saveSiteHolder(siteHolder);
        return "index";
    }

//    @PostMapping("/checkSiteNameExist")
//    public @ResponseBody Boolean checkSiteName(@RequestParam String siteName){
//        return siteService.isSiteNameExist(siteName);
//    }

    @PostMapping("/checkSiteHolderNameExist")
    public @ResponseBody Boolean checkSiteHolderName(@RequestParam String siteHolderName){
        List<SiteHolder> siteHolders = userService.getCurrentUser().getSiteHolders();
        boolean isUserSite = false;
        for(SiteHolder siteHolder : siteHolders){
            if(siteHolder.getSiteHolderName().equals(siteHolderName)) {isUserSite = true;}
        }
        return !(isUserSite || !siteHolderService.isSiteHolderNameExist(siteHolderName));
    }

    @GetMapping("/redactSite")
    public String redactSite(@RequestParam String siteHolderName, Model model) throws JsonProcessingException {
        SiteHolder siteHolder = siteHolderService.getBySiteHolderName(siteHolderName);
        SiteHolderDto siteHolderDto = new SiteHolderDto(siteHolder);
        model.addAttribute("siteHolderDto", siteHolderDto);
        System.out.println(siteHolderDto);
        return "constructor/index";
    }

    @PostMapping("/deleteSite")
    public @ResponseBody void deleteSite(@RequestParam long siteHolderId){
        siteHolderService.deleteSiteHolder(siteHolderId);
    }

}