package CaseStudy4.controller;

import CaseStudy4.model.Users;
import CaseStudy4.repository.IRoleRepository;
import CaseStudy4.service.Role.IRoleService;
import CaseStudy4.service.users.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IRoleService iRoleService;
    @Value("${upload.img}")
    private String upload_IMG;

    @GetMapping
    public ResponseEntity<Iterable<Users>> findAll() {
        return new ResponseEntity<>(iUserService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Iterable<Users>> save(@ModelAttribute Users users) {
        MultipartFile file_img = users.getImage();
        String fileName_IMG = file_img.getOriginalFilename();
        try {
            file_img.transferTo(new File(upload_IMG + fileName_IMG));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        iUserService.save(new Users(users.getUsername(), users.getPassword(), users.getName(), users.getAddress(), users.getEmail(), users.getPhone(), fileName_IMG, iRoleService.findById(2l).get()));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<Optional<Users>> findById(@ModelAttribute Long id) {
        return new ResponseEntity<>(iUserService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Optional<Users>> deleteById(@ModelAttribute Long id) {
        iUserService.remove(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Iterable<Users>> update(@ModelAttribute Users users) {
        try {
            Users oldUser = iUserService.findById(users.getId()).get();
            MultipartFile file_img = users.getImage();
            String fileName_IMG = file_img.getOriginalFilename();
            if (Objects.equals(fileName_IMG, "")) {
                fileName_IMG = oldUser.getAvatar();
            } else {
                file_img.transferTo(new File(upload_IMG + fileName_IMG));
            }
            iUserService.save(new Users(users.getId(), users.getUsername(), users.getPassword(), users.getName(), users.getAddress(), users.getEmail(), users.getPhone(), fileName_IMG, oldUser.getRole()));
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/changePassword/{id}")
    public ResponseEntity<Iterable<Users>> changePassword(@RequestBody String newPassword, @PathVariable("id") Long id) {
        iUserService.updatePasswordByID(newPassword, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
