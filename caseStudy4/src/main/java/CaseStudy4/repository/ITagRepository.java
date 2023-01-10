package CaseStudy4.repository;

import CaseStudy4.model.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ITagRepository extends JpaRepository<Tags, Long> {

}
