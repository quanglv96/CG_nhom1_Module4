package CaseStudy4.service.comment;

import CaseStudy4.model.Comments;
import CaseStudy4.model.Playlist;
import CaseStudy4.model.Songs;
import CaseStudy4.repository.ICommentRepository;
import CaseStudy4.service.Songs.ISongService;
import CaseStudy4.service.playlist.IPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService implements ICommentService {
    @Autowired
    private ICommentRepository iCommentRepository;
    @Autowired
    private ISongService iSongService;
    @Autowired
    private IPlaylistService iPlaylistService;

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
    public Iterable<Comments> findAllBySongsOrderByDateDesc(Songs songs) {
        return iCommentRepository.findAllBySongsOrderByDateDesc(songs);
    }

    @Override
    public Iterable<Comments> findAllByPlaylistOrderByDateDesc(Playlist playlist) {
        return iCommentRepository.findAllByPlaylistOrderByDateDesc(playlist);
    }

}
