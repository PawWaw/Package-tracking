package pl.polsl.controller;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.polsl.model.inPostModels.InPostDetails;

public interface InPostDetailsRepository extends MongoRepository<InPostDetails, String> {
    public InPostDetails findByStatus(String status);
}