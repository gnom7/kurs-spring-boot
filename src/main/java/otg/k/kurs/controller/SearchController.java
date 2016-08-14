package otg.k.kurs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import otg.k.kurs.domain.Site;
import otg.k.kurs.domain.User;
import otg.k.kurs.dto.SiteDto;
import otg.k.kurs.search.SiteSearch;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private SiteSearch siteSearch;

    @GetMapping("/search")
    public String search(@RequestParam String q, Model model) {
        List<Site> searchResults = null;
        try {
            searchResults = siteSearch.search(q);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        if(searchResults == null) return "search/search";

        List<SiteDto> results = prepareDto(searchResults);
        model.addAttribute("searchResults", results);
        model.addAttribute("searchRequest", q);
        return "search/search";
    }

    private List<SiteDto> prepareDto(List<Site> searchResults){
        List<SiteDto> results = new ArrayList<>(searchResults.size());
        for(Site site : searchResults){
            results.add(new SiteDto(site));
        }
        return results;
    }
}
