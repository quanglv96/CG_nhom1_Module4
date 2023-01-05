package CaseStudy4.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    private String description; // mô tả nội dung bài hát
    private LocalDate date_create; // ngày tạo
    private LocalDate last_update; // ngày cập nhập lần cuối
    @NotNull
    @OneToOne(targetEntity = Users.class)
    @JoinColumn(name = "id_users")
    private Users users;// ngươi tạo playlist
    @ManyToMany(targetEntity = Songs.class)
    @JoinTable(name = "playlist_song",joinColumns = {@JoinColumn(name = "id_playlist")},
            inverseJoinColumns = {@JoinColumn(name = "id_songs")})
    private List<Songs> songsList;
    @ManyToMany(targetEntity = Tags.class)
    @JoinTable( name = "playlist_tag",
            joinColumns = {@JoinColumn(name = "id_playlist")},
            inverseJoinColumns = {@JoinColumn(name = "id_tags")})
    private List<Tags> tagsList;
    private long views;
    private long likes;
}
