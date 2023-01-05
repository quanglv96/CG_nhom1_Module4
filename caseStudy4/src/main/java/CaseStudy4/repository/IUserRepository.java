package CaseStudy4.repository;

import CaseStudy4.model.Songs;
import CaseStudy4.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUserRepository extends JpaRepository<Users,Long> {
    @Query(value = "update Users set password=:newPass where id=:id")
    void updatePasswordByID(String newPass, Long id);
    Iterable<Users> findAllByNameContaining(String name);
}
