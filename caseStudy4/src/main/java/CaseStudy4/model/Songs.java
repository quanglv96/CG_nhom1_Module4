package CaseStudy4.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Songs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    private String audio;
    private String avatar;
    @NotNull
    @OneToOne(targetEntity = Users.class)
    @JoinColumn(name = "id_users")
    private Users users;
    @ManyToMany(targetEntity = Singer.class)
    @JoinTable(name = "song_singer",joinColumns = {@JoinColumn(name = "id_song")},
            inverseJoinColumns = {@JoinColumn(name = "id_singer")})
    private List<Singer> singerList;
    private String composer; // người sáng tác
    private LocalDate date;
    @ManyToMany(targetEntity = Tags.class)
    @JoinTable( name = "song_tag",joinColumns = {@JoinColumn(name = "id_song")},
            inverseJoinColumns = {@JoinColumn(name = "id_tags")})
    private List<Tags> tagsList;
    private long views;
    private long likes;
    @Transient
    private MultipartFile mp3;
    @Transient
    private MultipartFile image;
}
