package otg.k.kurs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import otg.k.kurs.domain.User;
import otg.k.kurs.dto.UserDto;
import otg.k.kurs.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        return "auth/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new UserDto());
        return "auth/registration";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Valid UserDto user,
                           BindingResult result, HttpServletRequest request,
                           Model model) {
        model.addAttribute(user);
        if (result.hasErrors()) {
            return "auth/registration";
        }
        if (!userService.registerUser(user, request)) {
            result.rejectValue("email", "registration.userExist");
            return "auth/registration";
        }
        return "auth/complete";
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("confirm_token") String token) {
        return userService.activateUser(token) ?
                "redirect:/login?activate=true" : "redirect:/auth-error?confirm_token=" + token;
    }

    @GetMapping("/auth-error")
    public String error(Model model, @RequestParam("confirm_token") String token) {
        model.addAttribute("authError", true);
        model.addAttribute("oldToken", token);
        return "auth/complete";
    }

    @GetMapping("/resend-confirm")
    public String resend(@RequestParam String token, HttpServletRequest request) {
        userService.resendConfirmationMessage(request, token);
        return "auth/complete";
    }

}