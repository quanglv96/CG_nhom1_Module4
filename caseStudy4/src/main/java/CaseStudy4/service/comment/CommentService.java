package CaseStudy4.service.comment;

import CaseStudy4.model.Comments;
import CaseStudy4.repository.ICommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService implements ICommentService {
    @Autowired
    private ICommentRepository iCommentRepository;

    @Override
    public Iterable<Comments> findAll() {
        return iCommentRepository.findAll();
    }

    @Override
    public Optional<Comments> findById(Long id) {
        return iCommentRepository.findById(id);
    }

    @Override
    public Comments save(Comments comments) {
        return iCommentRepository.save(comments);
    }

    @Override
    public void remove(Long id) {
        iCommentRepository.deleteById(id);
    }

    @Override
    public Iterable<Comments> findAllBySongsOrderByDateDesc(Long id) {
        return iCommentRepository.findAllBySongsOrderByDateDesc(id);
    }

    @Override
    public Iterable<Comments> findAllByPlaylistOrderByDateDesc(Long id) {
        return iCommentRepository.findAllByPlaylistOrderByDateDesc(id);
    }
}
