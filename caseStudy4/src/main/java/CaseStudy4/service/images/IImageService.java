package CaseStudy4.service.images;


import CaseStudy4.model.images;
import CaseStudy4.service.IGeneralService;

public interface IImageService  {
    Boolean checkImage(String name);
    void save(String name);

}
