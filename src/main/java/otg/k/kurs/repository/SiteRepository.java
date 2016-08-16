package otg.k.kurs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import otg.k.kurs.domain.Site;
import otg.k.kurs.domain.User;

import java.util.List;

public interface SiteRepository extends JpaRepository<Site, String> {

    List<Site> findByUser(User User);

    List<Site> findBySiteName(String siteName);

}
