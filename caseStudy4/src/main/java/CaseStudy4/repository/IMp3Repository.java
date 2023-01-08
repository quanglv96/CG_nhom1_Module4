package CaseStudy4.repository;

import CaseStudy4.model.mp3;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMp3Repository extends JpaRepository<mp3,Long> {
    int countByName(String name);
}
