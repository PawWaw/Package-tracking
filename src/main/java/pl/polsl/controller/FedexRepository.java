package pl.polsl.controller;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.polsl.model.fedexModels.Fedex;

public interface FedexRepository extends MongoRepository<Fedex, String> {
    public Fedex findByCode(String code);
}