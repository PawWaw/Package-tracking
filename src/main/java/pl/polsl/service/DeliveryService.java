package pl.polsl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import pl.polsl.model.Delivery;

@Service
public class DeliveryService {

//    @Autowired
//    DeliveryRepository repository;

    public void createDelivery(Delivery delivery) {
        //repository.save(delivery);
    }
}
