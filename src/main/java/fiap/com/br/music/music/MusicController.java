package fiap.com.br.music.music;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/musics")
@RequiredArgsConstructor
public class MusicController {

    private final MusicRepository musicRepository;

    @GetMapping("search")
    public List<Music> search(@RequestParam String title){
        return musicRepository.findByTitleContainingIgnoreCase(title);
    }

}
