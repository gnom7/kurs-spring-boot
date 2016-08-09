package otg.k.kurs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import otg.k.kurs.domain.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {

    Video findByVideoId(Long videoId);

}
