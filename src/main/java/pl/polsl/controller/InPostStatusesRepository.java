package pl.polsl.controller;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.polsl.model.inPostModels.InPostStatuses;

public interface InPostStatusesRepository extends MongoRepository<InPostStatuses, String> {
    public InPostStatuses findByHref(String status);
}
