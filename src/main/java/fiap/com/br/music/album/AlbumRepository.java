package fiap.com.br.music.album;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    List<Album> findByArtistId(Long idArtist);

    Page<AlbumProjections> findByReleaseDateBetween(Integer from, Integer to, Pageable pageable);

    @Query("SELECT a.title, a.releaseDate FROM Album a WHERE a.title = :criterio") //JPQL
    List<Album> findByCoisaMuitoParticular(String criterio);
}
