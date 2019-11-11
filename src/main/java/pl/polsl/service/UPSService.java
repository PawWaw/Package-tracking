package pl.polsl.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.controller.UPSRepository;
import pl.polsl.model.UPS;

import java.io.IOException;

@Service
public class UPSService {

    @Autowired
    private UPSRepository repository;

    public UPS getPackage(String code) throws IOException {

        HttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost httppost = new HttpPost("https://wwwcie.ups.com/rest/Track");

        StringEntity params = new StringEntity("{\"UPSSecurity\":{\"UsernameToken\":{\"Username\": \"pawelwaw123\",\"Password\":\"last-legi0n\"},\"ServiceAccessToken\":{\"AccessLicenseNumber\":\"6D6FFE40C320D695\"}},\"TrackRequest\":{\"Request\":{\"RequestOption\":\"1\"},\"InquiryNumber\":\""+ code +"\"}} ");

        httppost.setEntity(params);

        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();

        ObjectMapper objectMapper = new ObjectMapper();
        UPS tracking = objectMapper.readValue(entity.getContent(), UPS.class);
        tracking.setCode(code);
        repository.save(tracking);
        return tracking;
    }
}
