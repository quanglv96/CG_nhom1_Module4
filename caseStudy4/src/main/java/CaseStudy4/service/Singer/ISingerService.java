package CaseStudy4.service.Singer;

import CaseStudy4.model.Singer;
import CaseStudy4.service.IGeneralService;

import java.util.List;

public interface ISingerService extends IGeneralService<Singer> {
    Iterable<Singer> findAllByName(String name);
    void addSingerSong(Long idSong, Long idSinger);
    Iterable<Singer> StringToListObj(List<String> listSinger);
}
