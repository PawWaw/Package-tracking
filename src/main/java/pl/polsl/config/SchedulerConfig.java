package pl.polsl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import pl.polsl.controller.*;
import pl.polsl.model.*;
import pl.polsl.service.FedexService;
import pl.polsl.service.InPostService;
import pl.polsl.service.PocztaPolskaService;
import pl.polsl.service.UPSService;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    @Autowired
    InPostRepository inpostRepository;

    @Autowired
    InPostService inPostService;

    @Autowired
    PocztaPolskaRepository pocztaPolskaRepository;

    @Autowired
    PocztaPolskaService pocztaPolskaService;

    @Autowired
    UPSRepository upsRepository;

    @Autowired
    UPSService upsService;

    @Autowired
    FedexRepository fedexRepository;

    @Autowired
    FedexService fedexService;

    @Autowired
    MongoOperations mongoops;

    @Autowired
    EmailServiceImpl email;

    @Autowired
    UserRepository userRepository;

    @Scheduled(fixedDelay = 120000)
    public void scheduleFixedDelayTask() throws Exception {

        String userCode = "temp1";

        List<User> users = userRepository.findAll();
        String id = null;
        List<InPost> inPostChanges = new ArrayList<>();
        List<PocztaPolska> pocztaPolskaChanges = new ArrayList<>();
        List<UPS> upsListChanges = new ArrayList<>();
        List<Fedex> fedexListChanges = new ArrayList<>();

        for (User user : users) {
            Query inPostQuery = new Query();
            inPostQuery.addCriteria(Criteria.where("status").ne("delivered"));
            List<InPost> inPostList = mongoops.find(inPostQuery, InPost.class);

            for (InPost inPost : inPostList) {
                if (inPost.getUserCode().equals(userCode)) {
                    id = inPost.getId();
                    inPost.setId(null);
                    InPost temp = inPostService.getPackage(inPost.getCode());
                    if (!inPost.equals(temp)) {
                        temp.setId(id);
                        inPostChanges.add(temp);
                        inpostRepository.save(temp);
                    }
                }
            }

            Query pocztaPolskaQuery = new Query();
            pocztaPolskaQuery.addCriteria(Criteria.where("deliveredFlag").ne("true"));
            List<PocztaPolska> pocztaPolskaList = mongoops.find(pocztaPolskaQuery, PocztaPolska.class);

            for (PocztaPolska pocztaPolska : pocztaPolskaList) {
                if (pocztaPolska.getUserCode().equals(userCode)) {
                    id = pocztaPolska.getId();
                    pocztaPolska.setId(null);
                    PocztaPolska temp = pocztaPolskaService.getPackage(pocztaPolska.getCode());
                    if (!pocztaPolska.equals(temp)) {
                        temp.setId(id);
                        pocztaPolskaChanges.add(temp);
                        pocztaPolskaRepository.save(temp);
                    }
                }
            }

            Query upsQuery = new Query();
            upsQuery.addCriteria(Criteria.where("status").ne("D"));
            List<UPS> upsList = mongoops.find(upsQuery, UPS.class);

            for (UPS ups : upsList) {
                if (ups.getUserCode().equals(userCode)) {
                    id = ups.getId();
                    ups.setId(null);
                    UPS temp = upsService.getPackage(ups.getCode());
                    if (!ups.equals(temp)) {
                        temp.setId(id);
                        upsListChanges.add(temp);
                        upsRepository.save(temp);
                    }
                }
            }

            Query fedexQuery = new Query();
            fedexQuery.addCriteria(Criteria.where("Description").ne("DELIVERED"));
            List<Fedex> fedexList = mongoops.find(fedexQuery, Fedex.class);

            for (Fedex fedex : fedexList) {
                if (fedex.getUserCode().equals(userCode)) {
                    id = fedex.getId();
                    fedex.setId(null);
                    Fedex temp = fedexService.getPackage(fedex.getCode());
                    if (!fedex.equals(temp)) {
                        temp.setId(id);
                        fedexListChanges.add(temp);
                        fedexRepository.save(temp);
                    }
                }
            }

            if (inPostChanges.size() != 0 || pocztaPolskaChanges.size() != 0 || upsListChanges.size() != 0 || fedexListChanges.size() != 0)
                email.sendSimpleMessage(user.getEmail(), "<NoReply> Your packages changed their statuses!",
                        "Some of your packages changed their statuses. Please, login and check it out.");
        }
    }

}
