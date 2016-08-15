package otg.k.kurs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import otg.k.kurs.domain.SiteHolder;

public interface SiteHolderRepository extends JpaRepository<SiteHolder, String> {

    SiteHolder findBySiteHolderName(String siteHolderName);

}
