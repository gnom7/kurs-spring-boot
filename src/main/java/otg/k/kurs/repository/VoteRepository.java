package otg.k.kurs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import otg.k.kurs.domain.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
