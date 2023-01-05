package CaseStudy4.service.comment;

import CaseStudy4.model.Comments;
import CaseStudy4.service.IGeneralService;

public interface ICommentService extends IGeneralService<Comments> {
    Iterable<Comments> findAllBySongsOrderByDateDesc(Long id);
    Iterable<Comments> findAllByPlaylistOrderByDateDesc(Long id);
}
