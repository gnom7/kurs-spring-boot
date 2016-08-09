package otg.k.kurs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import otg.k.kurs.domain.Site;

public interface SiteRepository extends JpaRepository<Site, Long> {

    Site findByUsername(String username);

}
