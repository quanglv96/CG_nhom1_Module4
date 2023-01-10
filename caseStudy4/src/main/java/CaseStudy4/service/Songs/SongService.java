package CaseStudy4.service.Songs;

import CaseStudy4.model.Singer;
import CaseStudy4.model.Songs;
import CaseStudy4.model.Users;
import CaseStudy4.repository.ISongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SongService implements ISongService {
    @Autowired
    private ISongRepository isongRepository;

    @Override
    public Iterable<Songs> findAll() {
        isongRepository.setViewsAllSong();
        return isongRepository.findAll();
    }
    @Override
    public Iterable<Songs> listTrending() {
        return isongRepository.findAllByOrderByViewsDesc();
    }

    @Override
    public Iterable<Songs> findAllByNameContaining(String name) {
        return isongRepository.findAllByNameContaining(name);
    }

    @Override
    public Iterable<Songs> listNewSongs() {
        return isongRepository.findAllByOrderByDateDesc();
    }

    @Override
    public Iterable<Songs> findAllByUsers(Users users) {
        return isongRepository.findAllByUsers(users);
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

    @Override
    public Iterable<Songs> findAllBySingerList(Long id) {
        return isongRepository.findAllBySingerList(id);
    }

    @Override
    public List<Songs> generateFiveRandom(Long id) {
        List<Songs> result = new ArrayList<>();
        List<Songs> songsList = (List<Songs>) findAll();
        Set<Integer> indexes = new HashSet<>();
        while (indexes.size() <= 4) {
            Random rand = new Random();
            int index = rand.nextInt(songsList.size());
            if (Objects.equals(songsList.get(index).getId(), id)) {
                continue;
            }
            indexes.add(index);
        }
        for (int i : indexes) {
            result.add(songsList.get(i));
        }
        return result;
    }
}
