package CaseStudy4.service.Songs;

import CaseStudy4.model.Singer;
import CaseStudy4.model.Songs;
import CaseStudy4.model.Users;
import CaseStudy4.service.IGeneralService;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ISongService extends IGeneralService<Songs> {
    Iterable<Songs> listTrending();
    Iterable<Songs> findAllByNameContaining(String name);
    Iterable<Songs> listNewSongs();
    Iterable<Songs> findAllByUsers(Users users);

    Iterable<Songs> findAllBySingerList(Long id);

}
