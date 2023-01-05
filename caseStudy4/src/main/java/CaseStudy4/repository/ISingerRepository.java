package CaseStudy4.repository;

import CaseStudy4.model.Singer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISingerRepository extends JpaRepository<Singer, Long> {
}
