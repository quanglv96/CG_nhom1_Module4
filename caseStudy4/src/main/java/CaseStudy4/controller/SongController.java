package CaseStudy4.controller;

import CaseStudy4.model.Songs;
import CaseStudy4.service.Songs.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;

@RestController
@CrossOrigin("*")
@RequestMapping("/songs")
public class SongController {
    @Autowired
    private ISongService iSongService;
    @Value("${upload.img}")
    private String upload_IMG;
    @Value("${upload.mp3}")
    private String upload_MP3;
    @GetMapping
    public ResponseEntity<Iterable<Songs>> findAll(){
        return new ResponseEntity<>(iSongService.findAll(),HttpStatus.OK) ;
    }

    @GetMapping("/listTrending")
    public ResponseEntity<Iterable<Songs>> listTrending(){
        return new ResponseEntity<>(iSongService.listTrending(),HttpStatus.OK) ;
    }
    @PostMapping
    public ResponseEntity<Iterable<Songs>> save( @ModelAttribute Songs songs) {

        // up load ảnh
        MultipartFile file_img = songs.getImage();
        String fileName_IMG = file_img.getOriginalFilename();
        try {
            file_img.transferTo(new File(upload_IMG + fileName_IMG));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        // upload mp3
        MultipartFile file_mp3 = songs.getImage();
        String fileName_MP3 = file_mp3.getOriginalFilename();
        try {
            file_mp3.transferTo(new File(upload_IMG + fileName_MP3));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return new ResponseEntity<>(iSongService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Iterable<Songs>> delete(@PathVariable("id") Long id) {
        iSongService.remove(id);
        return new ResponseEntity<>(iSongService.findAll(), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Iterable<Songs>> update(@ModelAttribute Songs songs) {
        try {
            Songs oldSongs=iSongService.findById(songs.getId()).get();
            MultipartFile file_img = songs.getImage();
            String fileName_IMG = file_img.getOriginalFilename();
            if (Objects.equals(fileName_IMG, "")) {
                fileName_IMG = iSongService.findById(songs.getId()).get().getAvatar();
            } else {
                file_img.transferTo(new File(upload_IMG + fileName_IMG));
            }
            MultipartFile file_mp3 = songs.getImage();
            String fileName_MP3 = file_mp3.getOriginalFilename();
            if (Objects.equals(fileName_MP3, "")) {
                fileName_MP3 = iSongService.findById(songs.getId()).get().getAudio();
            } else {
                file_mp3.transferTo(new File(upload_IMG + fileName_MP3));
            }
            iSongService.save(new Songs(songs.getId(),songs.getName(),fileName_MP3,fileName_IMG,songs.getUsers(),songs.getSingerList(),songs.getComposer(),oldSongs.getDate(),songs.getTagsList(),oldSongs.getViews(),oldSongs.getLikes()));
            return new ResponseEntity<>(iSongService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
