package pl.polsl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.polsl.controller.DHLRepository;
import pl.polsl.model.dhlModels.DHL;
import pl.polsl.model.dhlModels.DHLEvent;

import java.util.ArrayList;
import java.util.List;

@Service
public class DHLService {

    @Autowired
    private DHLRepository repository;

    public List<DHL> getAll() {
        return null;
    }

    private DHL addPackage(String body, String userCode) {
        DHL tracking = parseToObject(body);
        tracking.setUserCode(userCode);
        DHL temp = repository.findByCode(tracking.getCode());
        if(temp == null)
            repository.save(tracking);
        else if(!temp.equals(tracking))
            repository.save(tracking);

        return tracking;
    }

    private DHL parseToObject(String body) {
        String temp = body.substring(body.lastIndexOf("<ns1:getTrackAndTraceInfoResponse>"), body.lastIndexOf("</getTrackAndTraceInfoResult>"));
        List<DHLEvent> list = new ArrayList<>();

        DHL tracking = new DHL();
        tracking.setCode(temp.substring(temp.indexOf("<shipmentId>") + 12, temp.indexOf("</shipmentId>")));
        tracking.setReceived_by(temp.substring(temp.indexOf("<receivedBy>") + 12, temp.indexOf("</receivedBy>")));
        while(true)
        {
            if(temp.contains("<item>"))
            {
                DHLEvent event = new DHLEvent();
                String element = temp.substring(temp.indexOf("<item>"), temp.indexOf("</item>"));

                event.setStatus(element.substring(element.indexOf("<status>") + 8, element.indexOf("</status>")));
                event.setDescription(element.substring(element.indexOf("<description>") + 13, element.indexOf("</description>")));
                event.setTerminal(element.substring(element.indexOf("<terminal>") + 10, element.indexOf("</terminal>")));
                event.setTimestamp(element.substring(element.indexOf("<timestamp>") + 11, element.indexOf("</timestamp>")));
                list.add(event);
                temp = temp.substring(temp.indexOf("</item>") + 7);
            }
            else
                break;
        }
        tracking.setEvents(list);
        tracking.setStatus(tracking.getEvents().get(tracking.getEvents().size() - 1).getStatus());

        return tracking;
    }

    public DHL getPackage(String code, String userCode) {

        DHL byCode = repository.findByCode(code);
        if(byCode != null)
            if(byCode.getStatus().equals("DOR"))
                return byCode;

        try {
            RestTemplate restTemplate = new RestTemplate();
            String xml =
                    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                            "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"https://sandbox.dhl24.com.pl/webapi2/provider/service.html?ws=1\">\n" +
                            "  <SOAP-ENV:Body>\n" +
                            "    <ns1:getTrackAndTraceInfo>\n" +
                            "      <authData>\n" +
                            "        <username>PAWEL_W</username>\n" +
                            "        <password>sXLbQ6Z5eOE!nak</password>\n" +
                            "      </authData>\n" +
                            "      <shipmentId>" + code + "</shipmentId>\n" +
                            "    </ns1:getTrackAndTraceInfo>\n" +
                            "  </SOAP-ENV:Body>\n" +
                            "</SOAP-ENV:Envelope>";

            ResponseEntity<String> response = restTemplate.postForEntity("https://sandbox.dhl24.com.pl/webapi2/provider/service.html?ws=1", xml, String.class);

            return addPackage(response.getBody(), userCode);

        } catch (Exception e) {
            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
            e.printStackTrace();
        }
        return null;
    }
}
