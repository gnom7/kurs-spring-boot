package otg.k.kurs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import otg.k.kurs.service.UserService;

@Controller
public class UserProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public String getUserProfile(@PathVariable String username, Model model){
        model.addAttribute("user", userService.getUserByUsername(username));
        return "profile/index";
    }

}
