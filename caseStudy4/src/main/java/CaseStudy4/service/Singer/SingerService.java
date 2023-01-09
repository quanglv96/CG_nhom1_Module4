package CaseStudy4.service.Singer;

import CaseStudy4.model.Singer;
import CaseStudy4.repository.ISingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
//@Transactional
public class SingerService implements ISingerService{
    @Autowired
    ISingerRepository singerRepository;

    @Override
    public Iterable<Singer> findAll() {
        return singerRepository.findAll();
    }

    @Override
    public Optional<Singer> findById(Long id) {
        return singerRepository.findById(id);
    }

    @Override
    public Singer save(Singer singer) {
        return singerRepository.save(singer);
    }

    @Override
    public void remove(Long id) {
        singerRepository.deleteById(id);
    }

    @Override
    public Iterable<Singer> findAllByName(String name) {
        return singerRepository.findAllByNameContaining(name);
    }
}
