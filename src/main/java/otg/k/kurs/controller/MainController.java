package otg.k.kurs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import otg.k.kurs.dto.SiteDto;

@Controller
public class MainController {

    @GetMapping("constructor")
    public String get(Model model){
        model.addAttribute("site", new SiteDto());
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

}