package CaseStudy4.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Data
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    // bắt validate mật khẩu từ 6 đến 8 kí tự
    @Pattern(regexp = "^.{6,8}$", message ="Passwords are 6-8 characters long")
    private String password;
    @NotNull
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Invalid name")
    private String name;
    @NotNull
    private String address;
    @Email(message = "Invalid email")
    private String email;
    @Pattern(regexp = "^(84|0[3|5|7|8|9])+([0-9]{8})\\b", message = "10 digit phone number")
    private String phone;
    private String avatar;
    @OneToOne(targetEntity = Role.class)
    @JoinColumn(name="id_role")
    private Role role;
    // ae tạo mới contructor theo logic cần sử dụng
    @Transient
    private MultipartFile image;

    public Users(String username, String password, String name, String address, String email, String phone, MultipartFile image) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.image = image;
    }

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Users(String username, String password, String name, String address, String email, String phone, String avatar, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
        this.role = role;
    }

    public Users(String username, String password, String name, String address, String email, String phone, String avatar) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
    }

    public Users(Long id, String username, String password, String name, String address, String email, String phone, String avatar, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
        this.role = role;
    }

}
