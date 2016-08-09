package otg.k.kurs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import otg.k.kurs.domain.Text;

public interface TextRepository extends JpaRepository<Text, Long> {

    Text findByTextId(Long textId);

}
