package CaseStudy4.service.users;

import CaseStudy4.model.Users;
import CaseStudy4.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public Iterable<Users> findAll() {
        return iUserRepository.findAll();
    }

    @Override
    public Optional<Users> findById(Long id) {
        return iUserRepository.findById(id);
    }

    @Override
    public Users save(Users users) {
        return iUserRepository.save(users);
    }

    @Override
    public void remove(Long id) {
        iUserRepository.deleteById(id);
    }

    @Override
    public void updatePasswordByID(String newPass, Long id) {
        iUserRepository.updatePasswordByID(newPass,id);
    }
}
