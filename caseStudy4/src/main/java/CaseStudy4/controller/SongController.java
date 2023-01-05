package CaseStudy4.controller;

import CaseStudy4.model.Songs;
import CaseStudy4.service.Songs.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/songs")
public class SongController {
    @Autowired
    private ISongService iSongService;
    @GetMapping("/listTrending")
    public ResponseEntity<Iterable<Songs>> listTrending(){
        return new ResponseEntity<>(iSongService.listTrending(),HttpStatus.OK) ;
    }
}
