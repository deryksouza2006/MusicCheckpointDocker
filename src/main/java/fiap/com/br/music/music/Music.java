package fiap.com.br.music.music;

import fiap.com.br.music.album.Album;
import fiap.com.br.music.genres.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private Integer durationSeconds; //Wrappers

    @ManyToMany
    private List<Genre> genres = new ArrayList<>();

    @ManyToOne
    private Album album;

}
