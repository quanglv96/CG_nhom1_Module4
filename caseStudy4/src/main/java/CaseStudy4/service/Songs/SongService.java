package CaseStudy4.service.Songs;

import CaseStudy4.model.Singer;
import CaseStudy4.model.Songs;
import CaseStudy4.model.Tags;
import CaseStudy4.model.Users;
import CaseStudy4.repository.ISingerRepository;
import CaseStudy4.repository.ISongRepository;
import CaseStudy4.repository.ITagRepository;
import CaseStudy4.service.Singer.ISingerService;
import CaseStudy4.service.Tags.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SongService implements ISongService {
    @Autowired
    private ISongRepository isongRepository;
    @Autowired
    private ITagService tagService;
    @Autowired
    private ISingerService singerService;

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
        isongRepository.save(songs);
        Songs newSong=isongRepository.findByName(songs.getName()).get();
        List<Tags> tagsList = newSong.getTagsList();
        for (int i = 0; i <  tagsList.size(); i++) {
            tagService.addSongerTag(newSong.getId(),tagsList.get(i).getId());
        }
        List<Singer> listSinger=newSong.getSingerList();
        for (int i = 0; i < listSinger.size(); i++) {
            singerService.addSingerSong(newSong.getId(),listSinger.get(i).getId());
        }
        return isongRepository.save(songs);
    }

    @Override
    public void remove(Long id) {
        isongRepository.deleteSongInPlaylist(id);
        isongRepository.deleteSongInTag(id);
        isongRepository.deleteSongInSinger(id);
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
