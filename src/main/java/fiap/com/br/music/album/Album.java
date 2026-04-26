package fiap.com.br.music.album;

import fiap.com.br.music.artist.Artist;
import fiap.com.br.music.music.Music;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    private Integer releaseDate;

    @ManyToOne
    private Artist artist;

    @OneToMany
    private List<Music> musics;

}


