package CaseStudy4.repository;

import CaseStudy4.model.images;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IImgRepository extends JpaRepository<images,Long> {
    int countByName(String name);
}
