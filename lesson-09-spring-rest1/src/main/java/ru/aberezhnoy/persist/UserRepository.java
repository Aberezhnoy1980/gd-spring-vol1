package ru.aberezhnoy.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    @Query("select distinct u " +
            "from User u left join fetch u.roles " +
            "where u.login = :login")
    Optional<User> findByLogin(String login);

    @Query("select u " +
            "from User u " +
            "where (u.login like :pattern or :pattern is null)")
    List<Product> findByFilter(@Param("pattern") String pattern);
}
