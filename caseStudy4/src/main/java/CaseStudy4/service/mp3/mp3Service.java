package CaseStudy4.service.mp3;

import CaseStudy4.model.images;
import CaseStudy4.model.mp3;
import CaseStudy4.repository.IMp3Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class mp3Service implements IMp3Service {
    @Autowired
    private IMp3Repository mp3Repository;

    @Override
    public Boolean checkMp3(String name) {
        if(mp3Repository.countByName(name)>0){
            return  true;
        }
        return false;
    }
    @Override
    public void save(String name) {
        mp3Repository.save(new mp3(name));
    }
}
