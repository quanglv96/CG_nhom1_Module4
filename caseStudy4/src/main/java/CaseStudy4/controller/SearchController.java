package CaseStudy4.controller;

import CaseStudy4.model.Comments;
import CaseStudy4.model.Playlist;
import CaseStudy4.model.Songs;
import CaseStudy4.model.Users;
import CaseStudy4.service.Singer.ISingerService;
import CaseStudy4.service.Songs.ISongService;
import CaseStudy4.service.Tags.ITagService;
import CaseStudy4.service.comment.ICommentService;
import CaseStudy4.service.playlist.IPlaylistService;
import CaseStudy4.service.users.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private ISongService iSongService;
    @Autowired
    private ITagService tagService;
    @Autowired
    private IPlaylistService iPlaylistService;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private ISingerService singerService;
    @Autowired
    private ICommentService iCommentService;

    @GetMapping
    public ResponseEntity<Object> search(@RequestParam("search") String text){
        List<Object> resultSearch=new ArrayList<>();
        resultSearch.add(findSongByName(text));
        resultSearch.add(findPlaylistByName(text));
        resultSearch.add(findUserByName(text));
        return new ResponseEntity<>(resultSearch,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> searchAll(@RequestParam("q") String text){
        Map<Integer, Integer> songComments = new TreeMap<>();
        Map<Integer, Integer> playlistComments = new TreeMap<>();
        List<Songs> songs = (List<Songs>) findSongByName(text);
        List<Playlist> playlists = (List<Playlist>) findPlaylistByName(text);
        for (Songs s : songs) {
            List<Comments> c = (List<Comments>) iCommentService.findAllBySongsOrderByDateDesc(s);
            songComments.put(Math.toIntExact(s.getId()), c.size());
        }
        for (Playlist p : playlists) {
            List<Comments> c = (List<Comments>) iCommentService.findAllByPlaylistOrderByDateDesc(p);
            playlistComments.put(Math.toIntExact(p.getId()), c.size());
        }
        List<Object> resultSearch = new ArrayList<>((Collection<?>) findSongByName(text));
        resultSearch.addAll((Collection<?>) findPlaylistByName(text));
        resultSearch.addAll((Collection<?>) findUserByName(text));
        return new ResponseEntity<>(new Object[]{resultSearch, tagService.findAll(), songComments, playlistComments}, HttpStatus.OK);
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
    @GetMapping("/songsBySinger")
    public ResponseEntity<Iterable<Songs>> findSongBySinger(@RequestParam("idSinger") Long idSinger){
        return new ResponseEntity<>(iSongService.findAllBySingerList(idSinger),HttpStatus.OK);
    }


}
