package CaseStudy4.controller;

import CaseStudy4.model.Playlist;
import CaseStudy4.model.Tags;
import CaseStudy4.model.Users;
import CaseStudy4.service.playlist.IPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.stylesheets.LinkStyle;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/playlist")
public class PlayListController {
    @Autowired
    private IPlaylistService iPlaylistService;
    @Value("${upload.img}")
    private String upload_IMG;

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
    @GetMapping("/newPlaylist")
    public ResponseEntity<Iterable<Playlist>> newPlaylist() {
        return new ResponseEntity<>(iPlaylistService.listNewPlaylist(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@ModelAttribute Playlist playlist, @ModelAttribute("userLogin")Users users) {
        MultipartFile file_img = playlist.getImage();
        String fileName_IMG = file_img.getOriginalFilename();
        try {
            file_img.transferTo(new File(upload_IMG + fileName_IMG));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        LocalDate date_create = LocalDate.now();
        LocalDate last_update = LocalDate.now();
Playlist newUser=new Playlist(playlist.getName(), playlist.getDescription(), fileName_IMG, date_create, last_update, users, 200, 200);
        iPlaylistService.save(newUser);
        return  ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Iterable<Playlist>> delete(@PathVariable("id") Long id) {
        iPlaylistService.remove(id);
        return new ResponseEntity<>(iPlaylistService.findAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Iterable<Playlist>> update(@ModelAttribute Playlist playlist) {
        try {
            MultipartFile file_img = playlist.getImage();
            String fileName_IMG = file_img.getOriginalFilename();
            if (Objects.equals(fileName_IMG, "")) {
                fileName_IMG = iPlaylistService.findById(playlist.getId()).get().getAvatar();
            } else {
                file_img.transferTo(new File(upload_IMG + fileName_IMG));
            }
            Playlist oldPlaylist = iPlaylistService.findById(playlist.getId()).get();
            LocalDate last_update = LocalDate.now();
            iPlaylistService.save(new Playlist(playlist.getId(), playlist.getName(), playlist.getDescription(), fileName_IMG, oldPlaylist.getDateCreate(), last_update, playlist.getUsers(), playlist.getSongsList(), playlist.getTagsList(), oldPlaylist.getViews(), oldPlaylist.getLikes()));
            return new ResponseEntity<>(iPlaylistService.findAll(), HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
        @PostMapping("/setLike/{id}")
        public void setLikes (@PathVariable("id") Long id){
            Playlist playlist = iPlaylistService.findById(id).get();
            playlist.setLikes(playlist.getLikes() + 1);
            iPlaylistService.save(playlist);
        }
        @PostMapping("/disLike/{id}")
        public void disLike (@PathVariable("id") Long id){
            Playlist playlist = iPlaylistService.findById(id).get();
            playlist.setLikes(playlist.getLikes() - 1);
            iPlaylistService.save(playlist);
        }

        @PostMapping("/setView/{id}")
        public void setView (@PathVariable("id") Long id){
            Playlist playlist = iPlaylistService.findById(id).get();
            playlist.setViews(playlist.getViews() + 1);
            iPlaylistService.save(playlist);
        }

    }
