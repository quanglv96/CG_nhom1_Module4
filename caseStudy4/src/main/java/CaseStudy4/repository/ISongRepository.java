package CaseStudy4.repository;

import CaseStudy4.model.Songs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISongRepository extends JpaRepository<Songs, Long> {
    Iterable<Songs> findAllByOrderByViewsDesc();
    Iterable<Songs> findAllByNameContaining(String name);

}
