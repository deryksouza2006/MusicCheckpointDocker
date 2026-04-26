package fiap.com.br.music.music;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusicRepository extends JpaRepository<Music, Long> {

    List<Music> findByTitleContainingIgnoreCase(String title);
}
