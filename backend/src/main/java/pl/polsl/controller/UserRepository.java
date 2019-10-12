package pl.polsl.controller;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.polsl.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    public User findByCode(String code);
}
