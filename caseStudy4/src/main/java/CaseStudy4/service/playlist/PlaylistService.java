package CaseStudy4.service.playlist;

import CaseStudy4.model.Playlist;

import CaseStudy4.model.Songs;
import CaseStudy4.model.Users;
import CaseStudy4.repository.IPlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        iPlaylistRepository.deletePlaylistInSongs(id);
        iPlaylistRepository.deletePlaylistInTag(id);
        iPlaylistRepository.deleteById(id);
    }

    @Override
    public Iterable<Playlist> listTrending() {
        return iPlaylistRepository.findAllByOrderByViewsDesc();
    }

    @Override
    public Iterable<Playlist> listNewPlaylist() {
        return iPlaylistRepository.findAllByOrderByDateCreateDesc();
    }

    @Override
    public Iterable<Playlist> findAllByUsers(Users users) {
        return iPlaylistRepository.findAllByUsers(users);
    }

    @Override
    public Iterable<Playlist> findAllByNameContaining(String name) {
        return iPlaylistRepository.findAllByNameContaining(name);
    }

    @Override
    public List<Playlist> generateFiveRandom(Long id) {
        List<Playlist> result = new ArrayList<>();
        List<Playlist> playlists = (List<Playlist>) findAll();
        Set<Integer> indexes = new HashSet<>();
        while (indexes.size() <= 4) {
            Random rand = new Random();
            int index = rand.nextInt(playlists.size());
            if (Objects.equals(playlists.get(index).getId(), id)) {
                continue;
            }
            indexes.add(index);
        }
        for (int i : indexes) {
            result.add(playlists.get(i));
        }
        return result;
    }
}
