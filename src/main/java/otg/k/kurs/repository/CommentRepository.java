package otg.k.kurs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import otg.k.kurs.domain.Comment;
import otg.k.kurs.domain.Site;
import otg.k.kurs.domain.User;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByUser(User user);

    List<Comment> findBySite(Site site);

}
