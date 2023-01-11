package CaseStudy4.controller;

import CaseStudy4.model.Users;

import CaseStudy4.service.Role.IRoleService;
import CaseStudy4.service.images.IImageService;
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
    @Autowired
    private IImageService imageService;
    @Value("${upload.img}")
    private String upload_IMG;

    @GetMapping
    public ResponseEntity<Iterable<Users>> findAll() {
        return new ResponseEntity<>(iUserService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity save(@ModelAttribute Users users) {
        if(iUserService.checkUsername(users.getUsername())){
            return new ResponseEntity(users, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        MultipartFile file_img = users.getImage();
        String fileName_IMG = file_img.getOriginalFilename();
        try {
            if(!imageService.checkImage(fileName_IMG)){
                imageService.save(fileName_IMG);
                file_img.transferTo(new File(upload_IMG + fileName_IMG));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        Users newUser=new Users(users.getUsername(), users.getPassword(), users.getName(), users.getAddress(), users.getEmail(), users.getPhone(), fileName_IMG, iRoleService.findById(2L).get());
        iUserService.save(newUser);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Users>> findById( @PathVariable Long id) {
        return new ResponseEntity<>(iUserService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Optional<Users>> deleteById(@ModelAttribute Long id) {
        iUserService.remove(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("{id}")
    public ResponseEntity<Optional<Users>> update(@ModelAttribute Users users) {
        try {
            Users oldUser = iUserService.findById(users.getId()).get();
            MultipartFile file_img = users.getImage();
            String fileName_IMG = file_img.getOriginalFilename();
            if (Objects.equals(fileName_IMG, "")) {
                fileName_IMG = oldUser.getAvatar();
            } else {
                file_img.transferTo(new File(upload_IMG + fileName_IMG));
            }
            Users newUser=new Users(users.getId(), oldUser.getUsername(), oldUser.getPassword(), users.getName(), users.getAddress(), users.getEmail(), users.getPhone(), fileName_IMG, oldUser.getRole());
            iUserService.save(newUser);
            return new ResponseEntity<>(iUserService.findById(newUser.getId()),HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users usersLogin){
        if(!iUserService.checkUsername(usersLogin.getUsername())){
            return new ResponseEntity<>("Tên đăng nhập không tồn tại", HttpStatus.NOT_FOUND);
        }
        Users users=iUserService.findUserByUsername(usersLogin.getUsername()).get();
        if(!users.getPassword().equals(usersLogin.getPassword())){
            return new ResponseEntity<>("mật khẩu sai ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return  new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<Iterable<Users>> changePassword(@RequestBody Users user) {
        iUserService.updatePasswordByID(user.getPassword(), user.getId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Users users){
        if(iUserService.checkUsername(users.getUsername())){
            return new ResponseEntity(users, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        iUserService.save(new Users(users.getUsername(),users.getPassword(),users.getName(),"","",users.getPhone(),"avt-defaut.png",iRoleService.findById(2L).get()));
        return  ResponseEntity.status(HttpStatus.OK).build();
    }
}
