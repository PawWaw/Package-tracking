package pl.polsl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.polsl.controller.UserRepository;
import pl.polsl.model.User;

import javax.validation.Valid;
import java.util.List;

@Service
public class UserService  implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    public String generateToken() {
        return null;
    }

    public ResponseEntity<Void> saveUser(User user) {
        if(user.getPassword().equals(user.getUsername()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        user.setPassword(getEncoder().encode(user.getPassword()));
        user.setId(null);
        user.setCode(user.getUsername());
        repository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public boolean loginUser(User user) {
        User temp = repository.findByCode(user.getUsername());
        if(temp != null)
        {
            user.setPassword(getEncoder().encode(user.getPassword()));
            return temp.getUsername().equals(user.getUsername()) && user.getPassword().equals(temp.getPassword());
        }
        return false;
    }

    public boolean createUser(@Valid User user) {
        if(user.getPassword().equals(user.getUsername()))
            return false;
        user.setPassword(getEncoder().encode(user.getPassword()));
        user.setId(null);
        user.setCode(user.getUsername());
        repository.save(user);
        return true;
    }

    public void deleteUser(String code) {
        User temp = repository.findByCode(code);
        repository.delete(temp);
    }

    public User getUser(String code) {
        return repository.findByCode(code);
    }

    public boolean checkIfExists(String username) {
        return repository.findByUsername(username) != null;
    }

    public void modifyUser(User user) {
        repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
