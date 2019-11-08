package pl.polsl.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.NodeList;
import pl.polsl.controller.PocztaPolskaRepository;

import javax.xml.namespace.*;
import javax.xml.soap.*;
import java.io.IOException;

public class PocztaPolskaService {

    @Autowired
    private PocztaPolskaRepository repository;

    public SOAPMessage getPackage(String code) throws IOException, SOAPException, JSONException {

        String soapEndpointUrl = "https://tt.poczta-polska.pl/Sledzenie/services/Sledzenie?wsdl";
        String soapAction = "http://sledzenie.pocztapolska.pl/sprawdzPrzesylke";
        SOAPMessage message = callPPWebService(soapEndpointUrl, soapAction, code);

        String body = message.getSOAPBody().getTextContent();
        JSONObject data = new JSONObject();
        data = XML.toJSONObject(body);

        return message;
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
            /*
            Constructed SOAP Request Message:
            <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:sled="http://sledzenie.pocztapolska.pl">
                <SOAP-ENV:Header/>
                <soapenv:Body>
                    <sled:witaj>
                        <sled:imie>Jan</sled:imie>
                    </sled:witaj>
                </soapenv:Body>
            </SOAP-ENV:Envelope>
            */

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

            // Print the SOAP Response
            System.out.println("Response SOAP Message:");
            soapResponse.writeTo(System.out);

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

        /* Print the request message, just for debugging purposes */
//        System.out.println("Request SOAP Message:");
//        soapMessage.writeTo(System.out);
//        System.out.println("\n");

        return soapMessage;
    }

}