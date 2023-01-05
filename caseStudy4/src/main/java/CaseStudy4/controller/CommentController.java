package CaseStudy4.controller;

import CaseStudy4.model.Comments;
import CaseStudy4.repository.ICommentRepository;
import CaseStudy4.service.comment.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private ICommentService iCommentService;
    @GetMapping("/song")
    public ResponseEntity<Iterable<Comments>> listCommentInSong(@RequestBody Long id){
        return new ResponseEntity<>(iCommentService.findAllBySongsOrderByDateDesc(id), HttpStatus.OK);
    }
    @GetMapping("/playlist")
    public ResponseEntity<Iterable<Comments>> listCommentInPlaylist(@RequestBody Long id){
        return new ResponseEntity<>(iCommentService.findAllByPlaylistOrderByDateDesc(id), HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteComment(@RequestBody Long id){
        iCommentService.remove(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
