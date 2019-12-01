package pl.polsl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnknownService {

    @Autowired
    private DHLService dhl;

    @Autowired
    private FedexService fedex;

    @Autowired
    private InPostService inPost;

    @Autowired
    private PocztaPolskaService pocztaPolska;

    @Autowired
    private UPSService ups;

    public Object getUnknown(String code, String userCode) {
        Object temp;
        try {
            temp =  dhl.getPackage(code, userCode);
            if(temp != null)
                return temp;
        } catch (Exception e) {
            System.out.println("Not DHL tracking number");
        }
        try {
            temp =  fedex.getPackage(code, userCode);
            if(temp != null)
                return temp;
        } catch (Exception e) {
            System.out.println("Not Fedex tracking number");
        }
        try {
            temp = inPost.getPackage(code, userCode);
            if(temp != null)
                return temp;
        } catch (Exception e) {
            System.out.println("Not InPost tracking number");
        }
        try {
            temp = pocztaPolska.getPackage(code, userCode);
            if(temp != null)
                return temp;
        } catch (Exception e) {
            System.out.println("Not PocztaPolska tracking number");
        }
        try {
            temp = ups.getPackage(code, userCode);
            if(temp != null)
                return temp;
        } catch (Exception e) {
            System.out.println("Not UPS tracking number");
        }
        return null;
    }
}
