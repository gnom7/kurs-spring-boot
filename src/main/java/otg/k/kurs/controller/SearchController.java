package otg.k.kurs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import otg.k.kurs.domain.Site;
import otg.k.kurs.domain.User;
import otg.k.kurs.dto.SiteDto;
import otg.k.kurs.dto.UserDto;
import otg.k.kurs.search.SiteSearch;
import otg.k.kurs.search.UserSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SearchController {

    @Autowired
    private SiteSearch siteSearch;

    @Autowired
    private UserSearch userSearch;

    @GetMapping("/search")
    public String search(@RequestParam String q, Model model) {
        List<Site> searchSites = null;
        List<User> searchUsers = null;
        searchSites = siteSearch.search(q);
        searchUsers = userSearch.search(q);

        if(searchSites != null) {
            List<SiteDto> siteResults = prepareSiteDto(searchSites);
            model.addAttribute("searchSites", siteResults);
        }
        if(searchUsers != null) {
            List<UserDto> userResults = prepareUserDto(searchUsers);
            model.addAttribute("searchUsers", userResults);
        }
        model.addAttribute("searchRequest", q);
        return "search/search";
    }

    private List<SiteDto> prepareSiteDto(List<Site> searchResults){
        List<SiteDto> results = new ArrayList<>(searchResults.size());
        results.addAll(searchResults.stream().map(SiteDto::new).collect(Collectors.toList()));
        return results;
    }
    private List<UserDto> prepareUserDto(List<User> searchResults){
        List<UserDto> results = new ArrayList<>(searchResults.size());
        results.addAll(searchResults.stream().map(UserDto::new).collect(Collectors.toList()));
        return results;
    }
}
