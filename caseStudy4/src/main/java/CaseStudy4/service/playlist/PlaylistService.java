package CaseStudy4.service.playlist;

import CaseStudy4.model.Playlist;

import CaseStudy4.repository.IPlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class PlaylistService implements IPlaylistService{
    @Autowired
    private IPlaylistRepository iPlaylistRepository;
    @Override
    public Iterable<Playlist> findAll() {
        iPlaylistRepository.setViewsAllPlaylist();
        return iPlaylistRepository.findAll();
    }

    @Override
    public Optional<Playlist> findById(Long id) {
        return iPlaylistRepository.findById(id);
    }

    @Override
    public Playlist save(Playlist playlist) {
        return iPlaylistRepository.save(playlist);
    }

    @Override
    public void remove(Long id) {
        iPlaylistRepository.deleteById(id);
    }

    @Override
    public Iterable<Playlist> listTrending() {
        return iPlaylistRepository.findAllByOrderByViewsDesc();
    }

    @Override
    public Iterable<Playlist> findAllByNameContaining(String name) {
        return iPlaylistRepository.findAllByNameContaining(name);
    }
}
