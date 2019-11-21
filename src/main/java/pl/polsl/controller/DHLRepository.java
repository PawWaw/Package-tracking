package pl.polsl.controller;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.polsl.model.DHL;

public interface DHLRepository extends MongoRepository<DHL, String> {
    public DHL findByCode(String code);
}
