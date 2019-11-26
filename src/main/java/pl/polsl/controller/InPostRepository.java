package pl.polsl.controller;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.polsl.model.inPostModels.InPost;

public interface InPostRepository extends MongoRepository<InPost, String> {
    public InPost findByCode(String code);
}