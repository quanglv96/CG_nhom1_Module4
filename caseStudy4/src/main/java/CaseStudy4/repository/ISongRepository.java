package CaseStudy4.repository;

import CaseStudy4.model.Singer;
import CaseStudy4.model.Songs;
import CaseStudy4.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface ISongRepository extends JpaRepository<Songs, Long> {
    Iterable<Songs> findAllByOrderByViewsDesc();
    Iterable<Songs> findAllByOrderByDateDesc();
    Iterable<Songs> findAllByUsers( Users users);

    Iterable<Songs> findAllByNameContaining(String name);
    @Query(value = "update Songs set views=(views+ 1)")
    void setViewsAllSong();
@Query(value = "select * from songs as s where s.id in(select ss.id_song from song_singer as ss where ss.id_singer:id",nativeQuery = true)
    Iterable<Songs> findAllBySingerList(Long id);
}
