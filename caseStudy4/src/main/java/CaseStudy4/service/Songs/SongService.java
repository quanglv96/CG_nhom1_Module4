package CaseStudy4.service.Songs;

import CaseStudy4.model.Songs;
import CaseStudy4.repository.ISongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SongService implements ISongService {
    @Autowired
    private ISongRepository isongRepository;

    @Override
    public Iterable<Songs> findAll() {
        return isongRepository.findAll();
    }
    @Override
    public Iterable<Songs> listTrending() {
        return isongRepository.findAllByOrderByViewsDesc();
    }

    @Override
    public Optional<Songs> findById(Long id) {
        return isongRepository.findById(id);
    }

    @Override
    public Songs save(Songs songs) {
        return isongRepository.save(songs);
    }

    @Override
    public void remove(Long id) {
        isongRepository.deleteById(id);
    }

}
