package pl.polsl.controller;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.polsl.model.upsModels.UPS;

public interface UPSRepository extends MongoRepository<UPS, String> {
    public UPS findByCode(String code);
}