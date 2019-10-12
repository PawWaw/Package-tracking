package pl.polsl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.controller.UserRepository;
import pl.polsl.model.User;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void createUser(User user) {
        repository.save(user);
    }

    public void deleteUser(String code) {
        User temp = repository.findByCode(code);
        repository.delete(temp);
    }

    public User getUser(String code) {
        return repository.findByCode(code);
    }

    public void modifyUser(User user) {
        repository.save(user);
    }
}
