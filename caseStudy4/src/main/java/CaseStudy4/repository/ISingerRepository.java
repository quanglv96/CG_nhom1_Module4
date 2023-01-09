package CaseStudy4.repository;

import CaseStudy4.model.Singer;
import CaseStudy4.model.Songs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISingerRepository extends JpaRepository<Singer, Long> {
    Iterable<Singer> findAllByNameContaining(String name);
}
