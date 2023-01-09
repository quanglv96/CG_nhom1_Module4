package CaseStudy4.controller;

import CaseStudy4.model.Playlist;
import CaseStudy4.model.Songs;
import CaseStudy4.model.Users;
import CaseStudy4.service.Singer.ISingerService;
import CaseStudy4.service.Songs.ISongService;
import CaseStudy4.service.playlist.IPlaylistService;
import CaseStudy4.service.users.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private ISongService iSongService;
    @Autowired
    private IPlaylistService iPlaylistService;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private ISingerService singerService;

    @GetMapping
    public ResponseEntity<Object> search(@RequestParam("search") String text){
        List<Object> resultSearch=new ArrayList<>();
        resultSearch.add(findSongByName(text));
        resultSearch.add(findPlaylistByName(text));
        resultSearch.add(findUserByName(text));
        return new ResponseEntity<>(resultSearch,HttpStatus.OK);
    }
    @GetMapping("/songs")
    public Iterable<Songs> findSongByName(@RequestParam("search") String text){
        return iSongService.findAllByNameContaining(text);
    }
    @GetMapping("/playlist")
    public Iterable<Playlist> findPlaylistByName(@RequestParam("search") String text){
        return iPlaylistService.findAllByNameContaining(text);
    }
    @GetMapping("/user")
    public Iterable<Users> findUserByName(@RequestParam("search") String text){
        return iUserService.findAllByNameContaining(text);
    }
    @GetMapping("/songsByUser")
    public ResponseEntity<Object> findSongByUser(@RequestParam("idUser") Long idUser){
        List<Object> resultSearch=new ArrayList<>();
        resultSearch.add(iUserService.findById(idUser).get());
        resultSearch.add(iSongService.findAllByUsers(iUserService.findById(idUser).get()));
        resultSearch.add(iPlaylistService.findAllByUsers(iUserService.findById(idUser).get()));
        return new ResponseEntity<>(resultSearch,HttpStatus.OK);
    }


}
