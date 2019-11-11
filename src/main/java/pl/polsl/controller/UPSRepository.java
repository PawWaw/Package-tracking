package pl.polsl.controller;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.polsl.model.UPS;

public interface UPSRepository extends MongoRepository<UPS, String> {
    public UPS findByCode(String code);
}