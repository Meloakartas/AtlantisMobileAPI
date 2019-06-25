package restapi.service;

import restapi.model.User;
import restapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserService implements IUserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    @Override
    public User findUserById(long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public User saveOrUpdateUser(User user){
        return repository.save(user);
    }

    public User findUserByUserADid(String userADid) {return repository.findUserByUserADid(userADid);}
}