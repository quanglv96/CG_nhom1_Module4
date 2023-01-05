package CaseStudy4.service.playlist;

import CaseStudy4.model.Playlist;

import CaseStudy4.model.Songs;
import CaseStudy4.service.IGeneralService;


public interface IPlaylistService extends IGeneralService<Playlist> {
    Iterable<Playlist> listTrending();
    Iterable<Playlist> findAllByNameContaining(String name);
}
