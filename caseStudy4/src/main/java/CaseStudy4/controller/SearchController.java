package CaseStudy4.controller;

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

    @GetMapping
    public ResponseEntity<Object> search(@RequestParam("search") String text){
        List resultSearch=new ArrayList<>();
        resultSearch.add(findSongByName(text));
        resultSearch.add(findPlaylistByName(text));
        resultSearch.add(findUserByName(text));
        return new ResponseEntity<>(resultSearch,HttpStatus.OK);
    }
    @GetMapping("/songs")
    public Iterable findSongByName(@RequestParam("search") String text){
        return iSongService.findAllByNameContaining(text);
    }
    @GetMapping("/playlist")
    public Iterable findPlaylistByName(@RequestParam("search") String text){
        return iPlaylistService.findAllByNameContaining(text);
    }
    @GetMapping("/user")
    public Iterable findUserByName(@RequestParam("search") String text){
        return iUserService.findAllByNameContaining(text);
    }

}
