package CaseStudy4.repository;

import CaseStudy4.model.Comments;
import CaseStudy4.model.Playlist;
import CaseStudy4.model.Songs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepository extends JpaRepository<Comments, Long> {
    Iterable<Comments> findAllBySongsOrderByIdDesc(Songs songs);
    Iterable<Comments> findAllByPlaylistOrderByIdDesc(Playlist playlist);

}
