package otg.k.kurs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import otg.k.kurs.domain.SiteHolder;
import otg.k.kurs.domain.User;
import otg.k.kurs.dto.SiteHolderDto;
import otg.k.kurs.dto.UserDto;
import otg.k.kurs.service.SiteHolderService;
import otg.k.kurs.service.UserService;

import java.io.IOException;
import java.util.stream.Collectors;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private SiteHolderService siteHolderService;

    @GetMapping("/admin")
    public String getAdminPanel(Model model){
        model.addAttribute("sites", siteHolderService.getAll()
                .stream().map(SiteHolderDto::new).collect(Collectors.toList()));
        model.addAttribute("users", userService.getAll()
                .stream().map(UserDto::new).collect(Collectors.toList()));
        return "admin/adminPanel";
    }

    @PostMapping("/deleteUser")
    public @ResponseBody void deleteUser(@RequestParam String username){
        userService.deleteUser(username);
    }

    @PostMapping("/addUser")
    public @ResponseBody void addUser(@RequestParam String userDto,
                                      @RequestParam(required = false) String password) throws IOException {
        userService.updateUserFromDto(new ObjectMapper().readValue(userDto, UserDto.class), password);
    }

    @PostMapping("addSite")
    public @ResponseBody void addSite(@RequestParam String siteHolderDto) throws IOException {
        siteHolderService.updateSiteHolderFromDto(new ObjectMapper()
                .readValue(siteHolderDto, SiteHolderDto.class));
    }

}
