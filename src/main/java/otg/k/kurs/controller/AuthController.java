package otg.k.kurs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import otg.k.kurs.domain.ForgotPasswordToken;
import otg.k.kurs.dto.AccountDto;
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
        model.addAttribute("user", new AccountDto());
        return "auth/registration";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Valid AccountDto user,
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
        return "redirect:/login?registerSuccess";
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("confirm_token") String token) {
        return userService.activateUser(token) ?
                "redirect:/login?activate=true" : "redirect:/login?wrongToken=" + token;
    }

    @GetMapping("/resend-confirm")
    public String resend(@RequestParam String email, HttpServletRequest request) {
        userService.resendConfirmationMessage(email, request);
        return "redirect:/login?resendConfirmEmailComplete";
    }

    @PostMapping("/forgotPassword")
    public String sendResetPasswordEmail(@RequestParam String email, Model model, HttpServletRequest request){
        userService.sendEmailToResetPassword(email, request);
        return "redirect:/login?resetPassword";
    }

    @GetMapping("/forgotPassword")
    public String getResetPasswordPage(@RequestParam String token, Model model){
        model.addAttribute("token", token);
        return "auth/forgotPassword";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestParam String newPassword,
                                @RequestParam String confirmPassword, @RequestParam String token){
        if( !newPassword.equals(confirmPassword) ) {
            return "auth/login";
        }
        userService.resetPassword(newPassword, token);
        return "redirect:/login?passwordReseted";
    }

}