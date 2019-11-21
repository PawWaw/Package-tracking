package pl.polsl.service;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

@Service
public class CommonsService {

    public String getUserFromJWT(String token) {
        String user = null;
        String[] split_string = token.split("\\.");
        String base64EncodedHeader = split_string[0];
        String base64EncodedBody = split_string[1];
        String base64EncodedSignature = split_string[2];

        System.out.println("~~~~~~~~~ JWT Header ~~~~~~~");
        Base64 base64Url = new Base64(true);
        String header = new String(base64Url.decode(base64EncodedHeader));
        System.out.println("JWT Header : " + header);


        System.out.println("~~~~~~~~~ JWT Body ~~~~~~~");
        String body = new String(base64Url.decode(base64EncodedBody));
        System.out.println("JWT Body : " + body);

        user = body.substring(body.indexOf("sub") + 6, body.indexOf("exp") - 3);
        return user;
    }

}
