package pl.polsl.controller;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.polsl.model.Fedex;

public interface FedexRepository extends MongoRepository<Fedex, String> {
    public Fedex findByCode(String code);
}
