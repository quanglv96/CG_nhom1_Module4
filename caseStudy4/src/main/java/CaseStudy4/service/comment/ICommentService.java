package CaseStudy4.service.comment;

import CaseStudy4.model.Comments;
import CaseStudy4.model.Playlist;
import CaseStudy4.model.Songs;
import CaseStudy4.service.IGeneralService;

public interface ICommentService extends IGeneralService<Comments> {
    Iterable<Comments> findAllBySongsOrderByDateDesc(Songs songs);
    Iterable<Comments> findAllByPlaylistOrderByDateDesc(Playlist playlist);
}
