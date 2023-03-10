package CaseStudy4.controller;

import CaseStudy4.model.Playlist;
import CaseStudy4.model.Tags;
import CaseStudy4.model.Users;
import CaseStudy4.service.Tags.ITagService;
import CaseStudy4.model.*;
import CaseStudy4.service.Songs.ISongService;
import CaseStudy4.service.comment.ICommentService;
import CaseStudy4.service.playlist.IPlaylistService;
import CaseStudy4.service.users.IUserService;
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
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/playlist")
public class PlayListController {
    @Autowired
    private IPlaylistService iPlaylistService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ITagService tagService;
    @Autowired
    private ISongService iSongService;
    @Autowired
    private ICommentService commentService;
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
    public ResponseEntity<?> save(@ModelAttribute Playlist playlist, @RequestParam("idUser") Long idUser) {
        MultipartFile file_img = playlist.getImage();
        String fileName_IMG = file_img.getOriginalFilename();
        try {
            file_img.transferTo(new File(upload_IMG + fileName_IMG));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        LocalDate date_create = LocalDate.now();
        LocalDate last_update = LocalDate.now();
        Users users=userService.findById(idUser).get();
        List<Tags> tagsList = editStringTag(playlist.getStringTag());
        Playlist newUser = new Playlist(playlist.getName(), playlist.getDescription(), fileName_IMG, date_create, last_update, users,tagsList, 200, 200);
        iPlaylistService.save(newUser);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Iterable<Playlist>> delete(@PathVariable("id") Long id) {
        iPlaylistService.remove(id);
        return new ResponseEntity<>(iPlaylistService.findAll(), HttpStatus.OK);
    }

    @PostMapping("{id}")
    public ResponseEntity<Iterable<Playlist>> update(@ModelAttribute Playlist playlist, @PathVariable("id") Long id) {
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
            List<Tags> tagsList = editStringTag(playlist.getStringTag());
            for (int i = 0; i <  tagsList.size(); i++) {
                tagService.addSongTag(id,tagsList.get(i).getId());
            }
            iPlaylistService.save(new Playlist(playlist.getId(), playlist.getName(), playlist.getDescription(), fileName_IMG, oldPlaylist.getDateCreate(), last_update, oldPlaylist.getUsers(), oldPlaylist.getSongsList(), tagsList, oldPlaylist.getViews(), oldPlaylist.getLikes()));
            return new ResponseEntity<>(iPlaylistService.findAll(), HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    public List<Tags> editStringTag(String tag) {
        List<Tags> listTag;
        String[] tags = tag.split("#");
        for (int i = 0; i < tags.length; i++) {
            if(!Objects.equals(tags[i], "")){
                //x??a kho???ng tr???ng
                tags[i]=  tags[i].replaceAll(" ", "");
                // lowe case
                tags[i]=tags[i].toLowerCase();
            }
        }
        // x??a tr??ng
        LinkedHashSet<String> hashSet = new LinkedHashSet<>(Arrays.asList(tags));
        tags = hashSet.toArray(new String[0]);
        // chuy???n tags v?? l??u
        listTag = (List<Tags>) tagService.StringToListObj(Arrays.asList(tags));
        return listTag;
    }
    @PostMapping("/setLike/{id}")
    public void setLikes(@PathVariable("id") Long id) {
        Playlist playlist = iPlaylistService.findById(id).get();
        playlist.setLikes(playlist.getLikes() + 1);
        iPlaylistService.save(playlist);
    }

    @PostMapping("/disLike/{id}")
    public void disLike(@PathVariable("id") Long id) {
        Playlist playlist = iPlaylistService.findById(id).get();
        playlist.setLikes(playlist.getLikes() - 1);
        iPlaylistService.save(playlist);
    }

    @PostMapping("/setView/{id}")
    public void setView(@PathVariable("id") Long id) {
        Playlist playlist = iPlaylistService.findById(id).get();
        playlist.setViews(playlist.getViews() + 1);
        iPlaylistService.save(playlist);
    }

    @GetMapping("/getView/{id}")
    public ResponseEntity<Object> getSongViews(@PathVariable("id") Long id) {
        Optional<Playlist> playlist = iPlaylistService.findById(id);
        if (playlist.isPresent()) {
            playlist.get().setViews(playlist.get().getViews() + 1);
            iPlaylistService.save(playlist.get());
            Iterable<Comments> comments = commentService.findAllByPlaylistOrderByDateDesc(playlist.get());
            List<Playlist> random = iPlaylistService.generateFiveRandom(id);
            List<Songs> songUpload = (List<Songs>) iSongService.findAllByUsers(playlist.get().getUsers());
            List<Playlist> playlistsUpload = (List<Playlist>) iPlaylistService.findAllByUsers(playlist.get().getUsers());
            Object[] result = new Object[]{playlist.get(), comments, random, new int[]{songUpload.size(), playlistsUpload.size()}};
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
