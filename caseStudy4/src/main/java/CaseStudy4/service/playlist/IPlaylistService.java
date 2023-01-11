package CaseStudy4.service.playlist;

import CaseStudy4.model.Playlist;

import CaseStudy4.model.Songs;
import CaseStudy4.model.Users;
import CaseStudy4.service.IGeneralService;

import java.util.List;


public interface IPlaylistService extends IGeneralService<Playlist> {
    Iterable<Playlist> listTrending();
    Iterable<Playlist> listNewPlaylist();
    Iterable<Playlist> findAllByUsers(Users users);

    Iterable<Playlist> findAllByNameContaining(String name);
    List<Playlist> generateFiveRandom(Long id);
}
