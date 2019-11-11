package pl.polsl.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.soap.SOAPMessage;

@Service
public class FedexService {

    public String getPackage(String code) {

        String message = callFedexService(code);

        return message;
    }

    private String callFedexService(String code) {

        SOAPMessage soapResponse = null;

        try {
            RestTemplate restTemplate = new RestTemplate();
            String xml =
                    "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:v16=\"http://fedex.com/ws/track/v16\">\n" +
                            "\t<SOAP-ENV:Header/>\n" +
                            "\t<soapenv:Body xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                            "\t\t<v16:TrackRequest>\n" +
                            "\t\t\t<v16:WebAuthenticationDetail>\n" +
                            "\t\t\t\t<v16:UserCredential>\n" +
                            "\t\t\t\t\t<v16:Key>snaKcUzGtUw3aP0u</v16:Key>\n" +
                            "\t\t\t\t\t<v16:Password>Cs9xKNd0YqA1vYS3V7RmjjmLF</v16:Password>\n" +
                            "\t\t\t\t</v16:UserCredential>\n" +
                            "\t\t\t</v16:WebAuthenticationDetail>\n" +
                            "\t\t\t<v16:ClientDetail>\n" +
                            "\t\t\t\t<v16:AccountNumber>510087860</v16:AccountNumber>\n" +
                            "\t\t\t\t<v16:MeterNumber>114017767</v16:MeterNumber>\n" +
                            "\t\t\t</v16:ClientDetail>\n" +
                            "\t\t\t<v16:TransactionDetail>\n" +
                            "\t\t\t\t<v16:CustomerTransactionId>Track By Number_v16</v16:CustomerTransactionId>\n" +
                            "\t\t\t\t<v16:Localization>\n" +
                            "\t\t\t\t\t<v16:LanguageCode>EN</v16:LanguageCode>\n" +
                            "\t\t\t\t\t<v16:LocaleCode>US</v16:LocaleCode>\n" +
                            "\t\t\t\t</v16:Localization>\n" +
                            "\t\t\t</v16:TransactionDetail>\n" +
                            "\t\t\t<v16:Version>\n" +
                            "\t\t\t\t<v16:ServiceId>trck</v16:ServiceId>\n" +
                            "\t\t\t\t<v16:Major>16</v16:Major>\n" +
                            "\t\t\t\t<v16:Intermediate>0</v16:Intermediate>\n" +
                            "\t\t\t\t<v16:Minor>0</v16:Minor>\n" +
                            "\t\t\t</v16:Version>\n" +
                            "\t\t\t<v16:SelectionDetails>\n" +
                            "\t\t\t\t<v16:CarrierCode>FDXE</v16:CarrierCode>\n" +
                            "\t\t\t\t<v16:PackageIdentifier>\n" +
                            "\t\t\t\t\t<v16:Type>TRACKING_NUMBER_OR_DOORTAG</v16:Type>\n" +
                            "\t\t\t\t\t<v16:Value>" + code + "</v16:Value>\n" +
                            "\t\t\t\t</v16:PackageIdentifier>\n" +
                            "\t\t\t\t<v16:ShipmentAccountNumber/>\n" +
                            "\t\t\t\t<v16:SecureSpodAccount/>\n" +
                            "\t\t\t\t<v16:Destination>\n" +
                            "\t\t\t\t\t<v16:GeographicCoordinates>rates evertitque aequora</v16:GeographicCoordinates>\n" +
                            "\t\t\t\t</v16:Destination>\n" +
                            "\t\t\t</v16:SelectionDetails>\n" +
                            "\t\t</v16:TrackRequest>\n" +
                            "\t</soapenv:Body>\n" +
                            "</SOAP-ENV:Envelope>";
            ResponseEntity<String> response = restTemplate.postForEntity("https://wsbeta.fedex.com:443/web-services", xml, String.class);

            return response.getBody();

        } catch (Exception e) {
            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
            e.printStackTrace();
        }
        return "null";
    }
}
