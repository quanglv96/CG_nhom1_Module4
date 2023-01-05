package CaseStudy4.controller;

import CaseStudy4.model.Playlist;
import CaseStudy4.model.Songs;
import CaseStudy4.service.playlist.IPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/playlist")
public class PlayListController {
    @Autowired
    private IPlaylistService iPlaylistService;

    @GetMapping
    public ResponseEntity<Iterable<Playlist>> findAll() {
        return new ResponseEntity<>(iPlaylistService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Playlist>> findByID(@PathVariable("id") Long id) {
        setView(id);
        return new ResponseEntity<>(iPlaylistService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/listTrending")
    public ResponseEntity<Iterable<Playlist>> listTrending() {
        return new ResponseEntity<>(iPlaylistService.listTrending(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Iterable<Playlist>> save(@ModelAttribute Playlist playlist) {
        LocalDate date_create = LocalDate.now();
        LocalDate last_update = LocalDate.now();
        iPlaylistService.save(new Playlist(playlist.getName(), playlist.getDescription(), date_create, last_update, playlist.getUsers(), playlist.getSongsList(), playlist.getTagsList(), 20, 20));
        return new ResponseEntity<>(iPlaylistService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Iterable<Playlist>> delete(@PathVariable("id") Long id) {
        iPlaylistService.remove(id);
        return new ResponseEntity<>(iPlaylistService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Iterable<Playlist>> update(@ModelAttribute Playlist playlist) {
        Playlist oldPlaylist=iPlaylistService.findById(playlist.getId()).get();
        LocalDate last_update = LocalDate.now();
        iPlaylistService.save(new Playlist(playlist.getId(),playlist.getName(), playlist.getDescription(), oldPlaylist.getDate_create(), last_update, playlist.getUsers(), playlist.getSongsList(), playlist.getTagsList(), oldPlaylist.getViews(), oldPlaylist.getLikes()));
        return new ResponseEntity<>(iPlaylistService.findAll(), HttpStatus.OK);
    }
    @PostMapping("/setLike/{id}")
    public void setLikes(@PathVariable("id") Long id){
        Playlist playlist=iPlaylistService.findById(id).get();
        playlist.setLikes(playlist.getLikes()+1);
        iPlaylistService.save(playlist);
    }
    @PostMapping("/disLike/{id}")
    public void disLike(@PathVariable("id") Long id){
        Playlist playlist=iPlaylistService.findById(id).get();
        playlist.setLikes(playlist.getLikes()-1);
        iPlaylistService.save(playlist);
    }

    @PostMapping("/setView/{id}")
    public void setView(@PathVariable("id") Long id){
        Playlist playlist=iPlaylistService.findById(id).get();
        playlist.setViews(playlist.getViews()+1);
        iPlaylistService.save(playlist);
    }

}
