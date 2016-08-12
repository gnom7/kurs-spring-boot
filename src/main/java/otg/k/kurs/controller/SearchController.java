package otg.k.kurs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import otg.k.kurs.domain.Site;
import otg.k.kurs.search.SiteSearch;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private SiteSearch siteSearch;

    @GetMapping("/search")
    public String search(String q, Model model) {
        List<Site> searchResults = null;
        try {
            searchResults = siteSearch.search(q);
        }
        catch (Exception ex) {

        }
        model.addAttribute("searchResults", searchResults);
        return "search/search";
    }

}
