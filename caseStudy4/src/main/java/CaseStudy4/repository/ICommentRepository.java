package CaseStudy4.repository;

import CaseStudy4.model.Comments;
import CaseStudy4.model.Playlist;
import CaseStudy4.model.Songs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentRepository extends JpaRepository<Comments, Long> {
    Iterable<Comments> findAllBySongsOrderByDateDesc(Songs songs);
    Iterable<Comments> findAllByPlaylistOrderByDateDesc(Playlist playlist);

}
