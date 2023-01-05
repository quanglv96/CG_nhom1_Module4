package CaseStudy4.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
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

    public Songs(String name, List<Singer> singerList, String composer, LocalDate date, List<Tags> tagsList, MultipartFile mp3, MultipartFile image) {
        this.name = name;
        this.singerList = singerList;
        this.composer = composer;
        this.date = date;
        this.tagsList = tagsList;
        this.mp3 = mp3;
        this.image = image;
    }

    public Songs(Long id, String name, String audio, String avatar, Users users, List<Singer> singerList, String composer, LocalDate date, List<Tags> tagsList, long views, long likes) {
        this.id = id;
        this.name = name;
        this.audio = audio;
        this.avatar = avatar;
        this.users = users;
        this.singerList = singerList;
        this.composer = composer;
        this.date = date;
        this.tagsList = tagsList;
        this.views = views;
        this.likes = likes;
    }

    public Songs(String name, String audio, String avatar, Users users, List<Singer> singerList, String composer, LocalDate date, List<Tags> tagsList, long views, long likes) {
        this.name = name;
        this.audio = audio;
        this.avatar = avatar;
        this.users = users;
        this.singerList = singerList;
        this.composer = composer;
        this.date = date;
        this.tagsList = tagsList;
        this.views = views;
        this.likes = likes;
    }

}
