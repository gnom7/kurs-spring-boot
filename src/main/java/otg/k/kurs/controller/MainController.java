package otg.k.kurs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("constructor")
    public String get(){
        return "constructor/index";
    }

}