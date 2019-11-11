package pl.polsl.service;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.controller.PocztaPolskaRepository;
import pl.polsl.model.PocztaPolska;
import pl.polsl.model.PocztaPolskaDetails;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class PocztaPolskaService {

    @Autowired
    private PocztaPolskaRepository repository;

    public PocztaPolska getPackage(String code) throws SOAPException, JSONException {

        String soapEndpointUrl = "https://tt.poczta-polska.pl/Sledzenie/services/Sledzenie?wsdl";
        String soapAction = "http://sledzenie.pocztapolska.pl/sprawdzPrzesylke";
        SOAPMessage message = callPPWebService(soapEndpointUrl, soapAction, code);

        PocztaPolska tracking = parseSOAPMessage(message);
        repository.save(tracking);

        return tracking;
    }

    private PocztaPolska parseSOAPMessage(SOAPMessage message) throws SOAPException {

        PocztaPolska model = new PocztaPolska();
        List<PocztaPolskaDetails> details = new ArrayList<>();

        model.setSendDate(message.getSOAPBody().getElementsByTagName("ax21:dataNadania").item(0).getTextContent());
        model.setSendCountry(message.getSOAPBody().getElementsByTagName("ax21:krajNadania").item(0).getTextContent());
        model.setArrivalCountry(message.getSOAPBody().getElementsByTagName("ax21:krajPrzezn").item(0).getTextContent());
        model.setCode(message.getSOAPBody().getElementsByTagName("ax21:numer").item(0).getTextContent());
        model.setSendPostOffice(message.getSOAPBody().getElementsByTagName("ax21:urzadNadania").item(0).getTextContent());
        model.setArrivalPostOffice(message.getSOAPBody().getElementsByTagName("ax21:urzadPrzezn").item(0).getTextContent());
        model.setDeliveredFlag(message.getSOAPBody().getElementsByTagName("ax21:zakonczonoObsluge").item(0).getTextContent());
        model.setPackageType(message.getSOAPBody().getElementsByTagName("ax21:rodzPrzes").item(0).getTextContent());

        for (int i = 0; i < message.getSOAPBody().getElementsByTagName("ax21:zdarzenie").getLength(); i++) {
            PocztaPolskaDetails detail = new PocztaPolskaDetails();
            detail.setDatetime(message.getSOAPBody().getElementsByTagName("ax21:zdarzenie").item(i).getFirstChild().getTextContent());
            detail.setPlace(message.getSOAPBody().getElementsByTagName("ax21:zdarzenie").item(i).getFirstChild().getNextSibling().getTextContent());
            detail.setEvent_code(message.getSOAPBody().getElementsByTagName("ax21:zdarzenie").item(i).getFirstChild().getNextSibling().getNextSibling().getTextContent());
            detail.setEndFlag(message.getSOAPBody().getElementsByTagName("ax21:zdarzenie").item(i).getFirstChild().getNextSibling().getNextSibling().getNextSibling().getTextContent());
            detail.setDescription(message.getSOAPBody().getElementsByTagName("ax21:zdarzenie").item(i).getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getTextContent());
            details.add(detail);
        }

        model.setEvents(details);

        return model;
    }

    private void createSoapEnvelope(SOAPMessage soapMessage, String code) throws SOAPException {
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String myNamespace = "sled";
        String myNamespaceURI = "http://sledzenie.pocztapolska.pl";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();

        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);
        SOAPHeader header = envelope.getHeader();

        SOAPHeaderElement security =
                (SOAPHeaderElement) header.addChildElement("Security", "wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
        security.setMustUnderstand(true);

        SOAPElement usernameToken =
                security.addChildElement("UsernameToken", "wsse");
        usernameToken.addAttribute(new QName("xmlns:wsu"), "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
        usernameToken.addAttribute(new QName("wsu:Id"), "UsernameToken-2");

        SOAPElement username =
                usernameToken.addChildElement("Username", "wsse");
        username.addTextNode("sledzeniepp");

        SOAPElement password =
                usernameToken.addChildElement("Password", "wsse");

        password.setAttribute("Type", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
        password.addTextNode("PPSA");

        SOAPElement nonce =
                usernameToken.addChildElement("Nonce", "wsse");
        nonce.setAttribute("EncodingType", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary");
        nonce.addTextNode("X41PkdzntfgpowZsKegMFg==");

        SOAPElement created =
                usernameToken.addChildElement("Created", "wsu");
        created.addTextNode("2011-12-08T07:59:28.656Z");

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        soapBody.setPrefix("soapenv");
        SOAPElement soapBodyElem = soapBody.addChildElement("sprawdzPrzesylke", myNamespace);
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("imie", myNamespace);
        soapBodyElem1.addTextNode(code);
        soapMessage.saveChanges();
    }

    private SOAPMessage callPPWebService(String soapEndpointUrl, String soapAction, String code) {

        SOAPMessage soapResponse = null;

        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            soapResponse = soapConnection.call(createSOAPRequest(soapAction, code), soapEndpointUrl);

            soapConnection.close();
        } catch (Exception e) {
            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
            e.printStackTrace();
        }
        return soapResponse;
    }

    private SOAPMessage createSOAPRequest(String soapAction, String code) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        createSoapEnvelope(soapMessage, code);

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", soapAction);

        soapMessage.saveChanges();

        return soapMessage;
    }

}