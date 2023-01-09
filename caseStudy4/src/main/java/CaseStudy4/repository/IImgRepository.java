package CaseStudy4.repository;

import CaseStudy4.model.images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImgRepository extends JpaRepository<images,Long> {
    int countByName(String name);
}
