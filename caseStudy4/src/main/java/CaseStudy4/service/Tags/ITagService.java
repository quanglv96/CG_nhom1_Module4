package CaseStudy4.service.Tags;

import CaseStudy4.model.Tags;
import CaseStudy4.service.IGeneralService;

import java.util.List;

public interface ITagService extends IGeneralService <Tags> {
    Iterable<Tags> StringToListObj(List<String> listTag);
    void saveListTag(List<String> listTag);
    void addSongTag(Long idSong, Long idTag);
    void addPlaylistTag(Long idPlaylist,Long idTag);

}
