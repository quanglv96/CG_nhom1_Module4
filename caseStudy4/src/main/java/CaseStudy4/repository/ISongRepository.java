package CaseStudy4.repository;

import CaseStudy4.model.Songs;
import CaseStudy4.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.validation.constraints.NotNull;

public interface ISongRepository extends JpaRepository<Songs, Long> {
    Iterable<Songs> findAllByOrderByViewsDesc();
    Iterable<Songs> findAllByOrderByDateDesc();
    Iterable<Songs> findAllByUsers( Users users);

    Iterable<Songs> findAllByNameContaining(String name);
    @Query(value = "update Songs set views=(views+ 1)")
    void setViewsAllSong();


}
