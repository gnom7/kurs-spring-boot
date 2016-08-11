//package otg.k.kurs.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import otg.k.kurs.service.SiteService;
//
//@Controller
//public class SiteController {
//
//    @Autowired
//    private SiteService siteService;
//
//    @GetMapping("/{username}/{siteName}")
//    public String getSite(@RequestParam String username, String siteName){
//        System.out.println(username);
//        System.out.println(siteName);
//        return "index";
//    }
//
//}
