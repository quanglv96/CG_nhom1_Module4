package CaseStudy4.repository;

import CaseStudy4.model.Playlist;

import CaseStudy4.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPlaylistRepository extends JpaRepository<Playlist, Long> {
    Iterable<Playlist> findAllByOrderByViewsDesc();
    Iterable<Playlist> findAllByOrderByDateCreateDesc();
    Iterable<Playlist> findAllByUsers(Users users);
    Iterable<Playlist> findAllByNameContaining(String name);

    @Query(value = "update Playlist set views=(views+ 1)")
    void setViewsAllPlaylist();
}
