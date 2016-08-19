package otg.k.kurs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import otg.k.kurs.service.SiteHolderService;
import otg.k.kurs.service.UserService;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private SiteHolderService siteHolderService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String getAdminPanel(Model model){
        model.addAttribute("siteHolders", siteHolderService.getAll());
        model.addAttribute("users", userService.getAll());
        return "admin/adminPanel";
    }

}
