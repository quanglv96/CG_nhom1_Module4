package CaseStudy4.repository;

import CaseStudy4.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentRepository extends JpaRepository<Comments, Long> {
}
