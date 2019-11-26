package pl.polsl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import pl.polsl.controller.*;
import pl.polsl.model.User;
import pl.polsl.model.dhlModels.DHL;
import pl.polsl.model.fedexModels.Fedex;
import pl.polsl.model.inPostModels.InPost;
import pl.polsl.model.pocztaPolskaModels.PocztaPolska;
import pl.polsl.model.upsModels.UPS;
import pl.polsl.service.*;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    @Autowired
    DHLService dhlService;

    @Autowired
    DHLRepository dhlRepository;

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

    @Scheduled(fixedDelay = 1200000)
    public void scheduleFixedDelayTask() throws Exception {

        List<User> users = userRepository.findAll();
        String id = null;
        List<InPost> inPostChanges = new ArrayList<>();
        List<DHL> dhlChanges = new ArrayList<>();
        List<PocztaPolska> pocztaPolskaChanges = new ArrayList<>();
        List<UPS> upsListChanges = new ArrayList<>();
        List<Fedex> fedexListChanges = new ArrayList<>();

        for (User user : users) {
            Query dhlQuery = new Query();
            dhlQuery.addCriteria(Criteria.where("status").ne("DOR"));
            List<DHL> dhlList = mongoops.find(dhlQuery, DHL.class);

            for (DHL dhl : dhlList) {
                if (dhl.getUserCode().equals(user.getUsername())) {
                    id = dhl.getId();
                    dhl.setId(null);
                    DHL temp = dhlService.getPackage(dhl.getCode(), user.getUsername());
                    if (!dhl.equals(temp)) {
                        temp.setId(id);
                        dhlChanges.add(temp);
                        dhlRepository.save(temp);
                    }
                }
            }

            Query inPostQuery = new Query();
            inPostQuery.addCriteria(Criteria.where("status").ne("delivered"));
            List<InPost> inPostList = mongoops.find(inPostQuery, InPost.class);

            for (InPost inPost : inPostList) {
                if (inPost.getUserCode().equals(user.getUsername())) {
                    id = inPost.getId();
                    inPost.setId(null);
                    InPost temp = inPostService.getPackage(inPost.getCode(), user.getUsername());
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
                if (pocztaPolska.getUserCode().equals(user.getUsername())) {
                    PocztaPolska temp = pocztaPolskaService.getPackage(pocztaPolska.getCode(), user.getUsername());
                    if (!pocztaPolska.equals(temp)) {
                        pocztaPolskaChanges.add(temp);
                        pocztaPolskaRepository.save(temp);
                    }
                }
            }

            Query upsQuery = new Query();
            upsQuery.addCriteria(Criteria.where("status").ne("D"));
            List<UPS> upsList = mongoops.find(upsQuery, UPS.class);

            for (UPS ups : upsList) {
                if (ups.getUserCode().equals(user.getUsername())) {
                    id = ups.getId();
                    ups.setId(null);
                    UPS temp = upsService.getPackage(ups.getCode(), user.getUsername());
                    if (!ups.equals(temp)) {
                        temp.setId(id);
                        upsListChanges.add(temp);
                        upsRepository.save(temp);
                    }
                }
            }

            List<Fedex> fedexList = fedexRepository.findAll();

            for (Fedex fedex : fedexList) {
                if (fedex.getUserCode().equals(user.getUsername())) {
                    id = fedex.getId();
                    fedex.setId(null);
                    Fedex temp = fedexService.getPackage(fedex.getCode(), user.getUsername());
                    if (!fedex.getSize().equals(temp.getSize())) {
                        temp.setId(id);
                        fedexListChanges.add(temp);
                        fedexRepository.save(temp);
                    }
                }
            }

            if (inPostChanges.size() != 0 || pocztaPolskaChanges.size() != 0 || upsListChanges.size() != 0 || fedexListChanges.size() != 0 || dhlChanges.size() != 0) {
                String data = "";
                for (InPost inPostChange : inPostChanges)
                    data = data + inPostChanges.getClass() + ": " + inPostChange.getCode() + "\n";
                for (PocztaPolska pocztaPolskaChange : pocztaPolskaChanges)
                    data = data + pocztaPolskaChanges.getClass() + ": " + pocztaPolskaChange.getCode() + "\n";
                for (UPS upsListChange : upsListChanges)
                    data = data + upsListChange.getClass() + ": " + upsListChange.getCode() + "\n";
                for (Fedex fedexListChange : fedexListChanges)
                    data = data + fedexListChanges.getClass() + ": " + fedexListChange.getCode() + "\n";
                for (DHL dhlChange : dhlChanges)
                    data = data + dhlChanges.getClass() + ": " + dhlChange.getCode() + "\n";

                email.sendSimpleMessage(user.getEmail(), "<NoReply> Your packages changed their statuses!",
                        "Some of your packages changed their statuses. Codes:\n" +
                                data +
                                " Please, login and check it out.");
            }
        }
    }
}
