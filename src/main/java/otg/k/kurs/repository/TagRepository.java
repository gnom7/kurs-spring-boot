package otg.k.kurs.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import otg.k.kurs.domain.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Tag findByTag(String stringTag);

}