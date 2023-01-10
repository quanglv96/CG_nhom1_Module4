package CaseStudy4.repository;

import CaseStudy4.model.Singer;
import CaseStudy4.model.Songs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ISingerRepository extends JpaRepository<Singer, Long> {
    Iterable<Singer> findAllByNameContaining(String name);
    @Modifying
    @Query(value = "INSERT INTO casestudy4.song_singer (id_song, id_singer) VALUES (?1, ?2);",nativeQuery = true)
    void addSingerSong(Long idSong, Long idSinger);
}
