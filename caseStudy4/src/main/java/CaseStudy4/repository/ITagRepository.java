package CaseStudy4.repository;

import CaseStudy4.model.Tags;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITagRepository extends JpaRepository<Tags, Long> {
}
