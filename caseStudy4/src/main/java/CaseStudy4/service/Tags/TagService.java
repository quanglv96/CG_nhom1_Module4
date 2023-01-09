package CaseStudy4.service.Tags;

import CaseStudy4.model.Tags;
import CaseStudy4.repository.ITagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagService implements ITagService{
    @Autowired
    public ITagRepository iTagRepository;

    @Override
    public Iterable<Tags> findAll() {
        return iTagRepository.findAll();
    }

    @Override
    public Optional<Tags> findById(Long id) {
        return iTagRepository.findById(id);
    }

    @Override
    public Tags save(Tags tags) {
        return iTagRepository.save(tags);
    }

    @Override
    public void remove(Long id) {
        iTagRepository.deleteById(id);
    }

}
