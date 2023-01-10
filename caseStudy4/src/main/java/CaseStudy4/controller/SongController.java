package CaseStudy4.controller;

import CaseStudy4.model.Comments;
import CaseStudy4.model.Singer;
import CaseStudy4.model.Songs;
import CaseStudy4.model.Tags;
import CaseStudy4.model.Users;
import CaseStudy4.repository.ITagRepository;
import CaseStudy4.repository.IUserRepository;
import CaseStudy4.service.Singer.ISingerService;
import CaseStudy4.service.Songs.ISongService;
import CaseStudy4.service.comment.ICommentService;
import CaseStudy4.service.Tags.ITagService;
import CaseStudy4.service.users.IUserService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/songs")
public class SongController {
    @Autowired
    private ISongService iSongService;
    @Autowired
    private ISingerService singerService;
    @Autowired
    private ICommentService commentService;
    @Autowired
    private ITagService tagService;
    @Autowired
    private IUserService userService;
    @Value("${upload.img}")
    private String upload_IMG;
    @Value("${upload.mp3}")
    private String upload_MP3;

    @GetMapping
    public ResponseEntity<Iterable<Songs>> findAll() {
        return new ResponseEntity<>(iSongService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Songs>> findByID(@PathVariable("id") Long id){
//        setView(id);
        return new ResponseEntity<>(iSongService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/listTrending")
    public ResponseEntity<Iterable<Songs>> listTrending() {
        return new ResponseEntity<>(iSongService.listTrending(), HttpStatus.OK);
    }

    @GetMapping("/newSongs")
    public ResponseEntity<Iterable<Songs>> newSongs() {
        return new ResponseEntity<>(iSongService.listNewSongs(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Iterable<Songs>> save(@ModelAttribute Songs songs, @RequestParam("idUser") Long idusers) {

        // up load ảnh
        MultipartFile file_img = songs.getImage();
        String fileName_IMG = file_img.getOriginalFilename();
        try {
            file_img.transferTo(new File(upload_IMG + fileName_IMG));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        // upload mp3
        MultipartFile file_mp3 = songs.getMp3();
        String fileName_MP3 = file_mp3.getOriginalFilename();
        try {
            file_mp3.transferTo(new File(upload_MP3 + fileName_MP3));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        //TAG
        List<Tags> tagsList = editStringTag(songs.getListTag());
        List<Singer> listSinger=editStringSinger(songs.getListSinger());
        Users users=userService.findById(idusers).get();
        Songs newSong=new Songs(songs.getName(), fileName_MP3, fileName_IMG, users, listSinger, songs.getComposer(), LocalDate.now(), tagsList, 200, 25);
        iSongService.save(newSong);
        return new ResponseEntity<>(iSongService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Iterable<Songs>> delete(@PathVariable("id") Long id) {
        iSongService.remove(id);
        return new ResponseEntity<>(iSongService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/setLike/{id}")
    public ResponseEntity<Optional<Songs>> setLikes(@PathVariable("id") Long id, @RequestParam("config") int num) {
        Songs songs = iSongService.findById(id).get();
        songs.setLikes(songs.getLikes() + num);
        iSongService.save(songs);
        return new ResponseEntity<>(iSongService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/setView/{id}")
    public void setView(@PathVariable("id") Long id) {
        Songs songs = iSongService.findById(id).get();
        songs.setViews(songs.getViews() + 1);
        iSongService.save(songs);
    }

    @PostMapping("{id}")
    public ResponseEntity<Iterable<Songs>> update(@ModelAttribute Songs songs, @PathVariable("id") Long id) {
        try {

            Songs oldSongs = iSongService.findById(songs.getId()).get();
            //IMG
            MultipartFile file_img = songs.getImage();
            String fileName_IMG = file_img.getOriginalFilename();
            if (Objects.equals(fileName_IMG, "")) {
                fileName_IMG = iSongService.findById(id).get().getAvatar();
            } else {
                file_img.transferTo(new File(upload_IMG + fileName_IMG));
            }
            //MP3
            MultipartFile file_mp3 = songs.getMp3();
            String fileName_MP3 = file_mp3.getOriginalFilename();
            if (Objects.equals(fileName_MP3, "")) {
                fileName_MP3 = iSongService.findById(songs.getId()).get().getAudio();
            } else {
                file_mp3.transferTo(new File(upload_MP3 + fileName_MP3));
            }
            //TAG
            List<Tags> tagsList = editStringTag(songs.getListTag());
            for (int i = 0; i <  tagsList.size(); i++) {
                tagService.addSongerTag(id,tagsList.get(i).getId());
            }
            List<Singer> listSinger=editStringSinger(songs.getListSinger());
            for (int i = 0; i < listSinger.size(); i++) {
                singerService.addSingerSong(id,listSinger.get(i).getId());
            }
            Songs newSong = new Songs(id, songs.getName(), fileName_MP3, fileName_IMG, oldSongs.getUsers(), listSinger, songs.getComposer(), oldSongs.getDate(), tagsList, oldSongs.getViews(), oldSongs.getLikes());
            iSongService.save(newSong);
            return new ResponseEntity<>(iSongService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/singerList/{id}")
    public ResponseEntity<Iterable<Songs>> listSinger(@PathVariable("id") Long idSinger) {
        return new ResponseEntity<>(iSongService.findAllBySingerList(idSinger), HttpStatus.OK);
    }

    @GetMapping("/getView/{id}")
    public ResponseEntity<Object> getSongViews(@PathVariable("id") Long id) {
        Optional<Songs> songs = iSongService.findById(id);
        if (songs.isPresent()) {
            songs.get().setViews(songs.get().getViews() + 1);
            iSongService.save(songs.get());
            Iterable<Comments> comments = commentService.findAllBySongsOrderByDateDesc(songs.get());
            List<Songs> random = iSongService.generateFiveRandom(id);
            Object[] result = new Object[]{songs.get(), comments, random};
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public List<Tags> editStringTag(String tag) {
        List<Tags> listTag;
        String[] tags = tag.split("#");
        for (int i = 0; i < tags.length; i++) {
            if(!Objects.equals(tags[i], "")){
                //xóa khoảng trắng
                tags[i].replaceAll(" ", "");
                // lowe case
                tags[i].toLowerCase();
            }
        }
        // xóa trùng
        LinkedHashSet<String> hashSet = new LinkedHashSet<>(Arrays.asList(tags));
        tags = hashSet.toArray(new String[0]);
        // chuyển tags và lưu
        listTag = (List<Tags>) tagService.StringToListObj(Arrays.asList(tags));
        return listTag;
    }

    public List<Singer> editStringSinger(String singer) {
        List<Singer> listSinger;
        String[] singers = singer.split(", ");
        listSinger = (List<Singer>) singerService.StringToListObj(Arrays.asList(singers));
        return listSinger;
    }
}


