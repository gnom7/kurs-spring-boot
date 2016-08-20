package otg.k.kurs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
        User profileUser = userService.getUserByUsername(username);
        if(profileUser == null) return "/error";
        model.addAttribute("profileUser", new UserDto(profileUser));
        return "profile/index";
    }

    @PostMapping("/{username}/changeAvatar")
    public String changeAvatar(@RequestParam String avatarUrl,
                               @PathVariable String username, HttpServletRequest request){
        User user = userService.getUserByUsername(username);
        if( user != null &&
                ( username.equals(request.getRemoteUser()) || request.isUserInRole("ROLE_ADMIN") ) ) {
            user.setAvatarUrl(avatarUrl);
            userService.saveUser(user);
        }
        return "redirect:/" + username;
    }

    @PostMapping("/{username}/changePassword")
    public String changePassword(@RequestParam String oldPassword,
                                 @RequestParam String newPassword,
                                 @PathVariable String username,
                                 HttpServletRequest request){
        User user = userService.getUserByUsername(username);
        if(user == null) {return "redirect:/" + username + "?error=User doesn't exist";}
        if( !(username.equals(request.getRemoteUser()) || request.isUserInRole("ROLE_ADMIN")) ){
            return "redirect:/" + username + "?error=You have not permission to do that";
        }
        boolean succeeded = userService.changePassword(user, oldPassword, newPassword);
        if(!succeeded){ return "redirect:/" + username + "?error=An error has occurred";}
        return "redirect:/" + username;
    }

}
