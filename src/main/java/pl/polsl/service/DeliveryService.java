package pl.polsl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.controller.DeliveryRepository;
import pl.polsl.model.Delivery;

import java.util.List;

@Service
public class DeliveryService{

    @Autowired
    private DeliveryRepository repository;

    public void createDelivery(Delivery delivery) {
        repository.save(delivery);
    }

    public void deleteDelivery(String code) {
        Delivery temp = repository.findByCode(code);
        repository.delete(temp);
    }

    public Delivery getDelivery(String code) {
        return repository.findByCode(code);
    }

    public List<Delivery> getAllDeliveries() {
        return repository.findAll();
    }

    public void modifyDelivery(Delivery delivery) {
        repository.save(delivery);
    }
}
