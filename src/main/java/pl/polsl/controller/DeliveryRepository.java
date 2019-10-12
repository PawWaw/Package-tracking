package pl.polsl.controller;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.polsl.model.Delivery;

public interface DeliveryRepository extends MongoRepository<Delivery, String> {
    public Delivery findByCode(String code);
}