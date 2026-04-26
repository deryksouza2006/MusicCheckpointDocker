package fiap.com.br.music.config;

import fiap.com.br.music.album.Album;
import fiap.com.br.music.album.AlbumRepository;
import fiap.com.br.music.artist.Artist;
import fiap.com.br.music.artist.ArtistRepository;
import fiap.com.br.music.genres.Genre;
import fiap.com.br.music.genres.GenreRepository;
import fiap.com.br.music.music.Music;
import fiap.com.br.music.music.MusicRepository;
import lombok.RequiredArgsConstructor;
import jakarta.annotation.Nonnull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;
    private final GenreRepository genreRepository;
    private final MusicRepository musicRepository;

    @Override
    public void run(@Nonnull String... args) {
        var artists = List.of(
                Artist.builder().name("Anitta").country("Brasil").birthDate(LocalDate.of(1993, 3, 30)).build(),
                Artist.builder().name("Shakira").country("Colômbia").birthDate(LocalDate.of(1977, 2, 2)).build(),
                Artist.builder().name("Adele").country("Reino Unido").birthDate(LocalDate.of(1988, 5, 5)).build(),
                Artist.builder().name("Bruno Mars").country("Estados Unidos").birthDate(LocalDate.of(1985, 10, 8)).build(),
                Artist.builder().name("Roberto Carlos").country("Brasil").birthDate(LocalDate.of(1941, 4, 19)).build(),
                Artist.builder().name("Coldplay").country("Reino Unido").build(),
                Artist.builder().name("Ivete Sangalo").country("Brasil").birthDate(LocalDate.of(1972, 5, 27)).build(),
                Artist.builder().name("Juanes").country("Colômbia").birthDate(LocalDate.of(1972, 8, 9)).build(),
                Artist.builder().name("Marília Mendonça").country("Brasil").birthDate(LocalDate.of(1995, 7, 22)).build(),
                Artist.builder().name("Beyoncé").country("Estados Unidos").birthDate(LocalDate.of(1981, 9, 4)).build()
        );

        var pop = new Genre("POP");
        var rock = new Genre("ROCK");
        var samba = new Genre("SAMBA");
        var funk = new Genre("FUNK");
        var sertanejo = new Genre("SERTANEJO");

        var musics = List.of(
                buildMusic("Caneta Azul", 120, pop, sertanejo),
                buildMusic("Evidencias", 245, sertanejo),
                buildMusic("Fio de Cabelo", 230, sertanejo),
                buildMusic("Ai Se Eu Te Pego", 165, sertanejo, pop),
                buildMusic("Boate Azul", 260, sertanejo),
                buildMusic("Asa Branca", 215, sertanejo),

                buildMusic("Mas Que Nada", 175, samba),
                buildMusic("Aquarela do Brasil", 200, samba),
                buildMusic("Trem das Onze", 190, samba),
                buildMusic("Vou Festejar", 210, samba),
                buildMusic("Não Deixe o Samba Morrer", 225, samba),
                buildMusic("O Show Tem Que Continuar", 205, samba),

                buildMusic("Anna Julia", 205, rock),
                buildMusic("Tempo Perdido", 305, rock),
                buildMusic("Metamorfose Ambulante", 230, rock),
                buildMusic("Pais e Filhos", 295, rock),
                buildMusic("Pro Dia Nascer Feliz", 245, rock),
                buildMusic("Primeiros Erros", 220, rock),

                buildMusic("Show das Poderosas", 180, funk, pop),
                buildMusic("Vai Malandra", 200, funk, pop),
                buildMusic("Bum Bum Tam Tam", 175, funk),
                buildMusic("Envolvimento", 190, funk),
                buildMusic("Cerol na Mão", 185, funk),
                buildMusic("Olha a Explosão", 195, funk),

                buildMusic("Garota de Ipanema", 190, pop),
                buildMusic("Como Nossos Pais", 255, pop),
                buildMusic("Sozinho", 235, pop),
                buildMusic("Sorte Grande", 215, pop),
                buildMusic("Pra Você Guardei o Amor", 220, pop, rock),
                buildMusic("Velha Infância", 240, pop, rock)
        );

        artistRepository.saveAll(artists);
        genreRepository.saveAll(List.of(pop, rock, samba, funk, sertanejo));
        musics = musicRepository.saveAll(musics);

        var albums = List.of(
                Album.builder()
                        .title("Show das Poderosas")
                        .releaseDate(2013)
                        .artist(artists.get(0))
                        .musics(List.of(musics.get(18), musics.get(19), musics.get(24)))
                        .build(),
                Album.builder()
                        .title("Laundry Service")
                        .releaseDate(2001)
                        .artist(artists.get(1))
                        .musics(List.of(musics.get(7), musics.get(8), musics.get(9)))
                        .build(),
                Album.builder()
                        .title("30")
                        .releaseDate(2021)
                        .artist(artists.get(2))
                        .musics(List.of(musics.get(25), musics.get(28), musics.get(29)))
                        .build(),
                Album.builder()
                        .title("Bruno Mars Hits")
                        .releaseDate(2016)
                        .artist(artists.get(3))
                        .musics(List.of(musics.get(0), musics.get(3), musics.get(26)))
                        .build(),
                Album.builder()
                        .title("Acústico Roberto Carlos")
                        .releaseDate(1999)
                        .artist(artists.get(4))
                        .musics(List.of(musics.get(1), musics.get(4), musics.get(5)))
                        .build(),
                Album.builder()
                        .title("Funk Pop Brasil")
                        .releaseDate(2020)
                        .artist(artists.get(6))
                        .musics(List.of(musics.get(20), musics.get(21), musics.get(22), musics.get(23)))
                        .build()
        );

        albumRepository.saveAll(albums);
    }

    private Music buildMusic(String title, int durationSeconds, Genre... genres) {
        return Music.builder()
                .title(title)
                .durationSeconds(durationSeconds)
                .genres(List.of(genres))
                .build();
    }
}
