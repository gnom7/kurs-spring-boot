package otg.k.kurs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import otg.k.kurs.domain.Site;
import otg.k.kurs.domain.SiteHolder;
import otg.k.kurs.domain.User;

import java.util.List;

@RepositoryRestResource
public interface SiteHolderRepository extends JpaRepository<SiteHolder, Long> {

    SiteHolder findBySiteHolderName(@Param("siteHolderName") String siteHolderName);

    List<SiteHolder> findByUser(User user);

}
