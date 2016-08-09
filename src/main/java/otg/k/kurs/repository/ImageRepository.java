package otg.k.kurs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import otg.k.kurs.domain.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

    Image findByImageId(Long imageId);

}
