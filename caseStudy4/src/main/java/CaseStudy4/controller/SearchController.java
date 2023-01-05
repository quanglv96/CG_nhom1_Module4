package CaseStudy4.controller;

import CaseStudy4.service.Songs.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private ISongService iSongService;

    @GetMapping
    public ResponseEntity<Object> search(@RequestParam("search") String text){
        List<ResponseEntity<Iterable>> resultSearch=new ArrayList<>();
        resultSearch.add(findSongByName(text));
        return new ResponseEntity<>(resultSearch,HttpStatus.OK);
    }
    public ResponseEntity<Iterable> findSongByName(String text){
        return new ResponseEntity<>(iSongService.findAllByNameContaining(text),HttpStatus.OK);
    }

}
