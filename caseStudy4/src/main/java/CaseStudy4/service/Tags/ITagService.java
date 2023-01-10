package CaseStudy4.service.Tags;

import CaseStudy4.model.Songs;
import CaseStudy4.model.Tags;
import CaseStudy4.service.IGeneralService;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ITagService extends IGeneralService <Tags> {
    Iterable<Tags> StringToListObj(List<String> listTag);
    void saveListTag(List<String> listTag);
    void addSongerTag(Long idSong, Long idTag);

}
