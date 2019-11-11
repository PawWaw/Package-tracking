package pl.polsl.controller;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.polsl.model.PocztaPolska;

public interface PocztaPolskaRepository extends MongoRepository<PocztaPolska, String> {
    public PocztaPolska findByCode(String code);
}