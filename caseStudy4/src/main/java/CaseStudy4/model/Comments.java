package CaseStudy4.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String content;
    @NotNull
    @OneToOne
    @JoinColumn(name="id_users")
    private Users users;
    @OneToOne
    @JoinColumn(name="id_songs")
    private Songs songs;
    @OneToOne
    @JoinColumn(name="id_playlist")
    private Playlist playlist;

    public Comments(LocalDate date, String content, Users users, Songs songs, Playlist playlist) {
        this.date = date;
        this.content = content;
        this.users = users;
        this.songs = songs;
        this.playlist = playlist;
    }
}
