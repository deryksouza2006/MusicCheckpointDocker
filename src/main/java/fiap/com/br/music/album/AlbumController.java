package fiap.com.br.music.album;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumRepository albumRepository;

    @GetMapping("/by-artist/{idArtist}")
    public List<Album> findByArtist(@PathVariable Long idArtist){
        return albumRepository.findByArtistId(idArtist);
    }

    @GetMapping("year-range")
    public Page<AlbumProjections> findByYearRange(@RequestParam Integer from, @RequestParam Integer to, Pageable pageable) {
        return albumRepository.findByReleaseDateBetween(from, to, pageable);
    }

}
