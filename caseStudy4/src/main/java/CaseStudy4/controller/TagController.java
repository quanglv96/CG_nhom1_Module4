package CaseStudy4.controller;

import CaseStudy4.model.Tags;
import CaseStudy4.service.Tags.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/tags")
public class TagController {
    @Autowired
    public ITagService iTagService;
    @GetMapping
    public ResponseEntity <Iterable<Tags>> findAll() {
        return new ResponseEntity<>(iTagService.findAll(), HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity <Optional<Tags>> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(iTagService.findById(id), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity <Iterable<Tags>> save (@RequestBody Tags tags) {
        iTagService.save(new Tags(tags.getName()));
        return new ResponseEntity<>(iTagService.findAll(), HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<Optional<Tags>> update (@RequestBody Tags tags) {
        Tags oldTags = iTagService.findById(tags.getId()).get();
        iTagService.save(new Tags(oldTags.getId(),tags.getName()));
        return new ResponseEntity<>(iTagService.findById(oldTags.getId()), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity <Iterable<Tags>> delete (@PathVariable ("id") Long id) {
        iTagService.remove(id);
        return new ResponseEntity<>(iTagService.findAll(), HttpStatus.OK);
    }




}
