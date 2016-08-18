package otg.k.kurs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import otg.k.kurs.domain.User;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(@Param("username") String username);

    User findByEmail(String email);

}
