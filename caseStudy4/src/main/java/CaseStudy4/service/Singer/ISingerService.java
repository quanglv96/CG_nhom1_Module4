package CaseStudy4.service.Singer;

import CaseStudy4.model.Singer;
import CaseStudy4.service.IGeneralService;

public interface ISingerService extends IGeneralService<Singer> {
    Iterable<Singer> findAllByName(String name);
}
