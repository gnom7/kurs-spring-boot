package otg.k.kurs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import otg.k.kurs.domain.User;
import otg.k.kurs.dto.UserDto;
import otg.k.kurs.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public String getUserProfile(@PathVariable String username, Model model){
        model.addAttribute("user", userService.getUserByUsername(username));
        User currentUser = userService.getCurrentUser();
        model.addAttribute("currentUser", currentUser);
        User profileUser = userService.getUserByUsername(username);
        profileUser.setPassword(null);
        model.addAttribute("profileUser", profileUser);
        return "profile/index";
    }

    @PostMapping("/changeAvatar")
    public String changeAvatar(@RequestParam String avatarUrl){
        User user = userService.getCurrentUser();
        user.setAvatarUrl(avatarUrl);
        userService.saveUser(user);
        return "redirect:/" + user.getUsername();
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestParam String oldPassword,
                                 @RequestParam String newPassword,
                                 @RequestParam String confirmNewPassword,
                                 HttpServletRequest request, Model model){
        if( !newPassword.equals(confirmNewPassword) ){
            model.addAttribute("error", "Password don't match");
        } else {
            boolean succeeded = userService.changePassword(oldPassword, newPassword);
            if(!succeeded){model.addAttribute("error", "An error has occurred. Please try again");}
        }
        return "redirect:/" + request.getRemoteUser();
    }

}
