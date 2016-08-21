package otg.k.kurs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import otg.k.kurs.domain.SiteHolder;
import otg.k.kurs.domain.User;

import java.util.List;

public interface SiteHolderRepository extends JpaRepository<SiteHolder, Long> {

    SiteHolder findBySiteHolderName(String siteHolderName);

    List<SiteHolder> findByUser(User user);
}
