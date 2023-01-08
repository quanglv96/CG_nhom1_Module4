package CaseStudy4.service.images;

import CaseStudy4.model.images;
import CaseStudy4.repository.IImgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService implements IImageService{
    @Autowired
    private IImgRepository imgRepository;
    @Override
    public Boolean checkImage(String name) {
        if(imgRepository.countByName(name)>0){
            return  true;
        }
        return false;
    }

    @Override
    public void save(String name) {
        imgRepository.save(new images(name));
    }
}
