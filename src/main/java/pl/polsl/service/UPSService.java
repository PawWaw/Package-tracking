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
import pl.polsl.model.upsModels.UPS;

import java.io.IOException;
import java.util.List;

@Service
public class UPSService {

    @Autowired
    private UPSRepository repository;

    private void addPackage(UPS tracking) {

        UPS temp = repository.findByCode(tracking.getCode());
        if(temp == null)
            repository.save(tracking);
        else if (!temp.equals(tracking))
            tracking.setId(temp.getId());
            repository.save(tracking);
    }

    public List<UPS> getAll() {
        return repository.findAll();
    }

    public UPS getPackage(String code, String userCode) throws IOException {

        UPS byCode = repository.findByCode(code);
        if(byCode != null)
            if(byCode.getStatus().equals("D"))
                return byCode;

        HttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost httppost = new HttpPost("https://wwwcie.ups.com/rest/Track");

        StringEntity params = new StringEntity("{\"UPSSecurity\":{\"UsernameToken\":{\"Username\": \"pawelwaw123\",\"Password\":\"last-legi0n\"},\"ServiceAccessToken\":{\"AccessLicenseNumber\":\"6D6FFE40C320D695\"}},\"TrackRequest\":{\"Request\":{\"RequestOption\":\"1\"},\"InquiryNumber\":\""+ code +"\"}} ");

        httppost.setEntity(params);

        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();

        ObjectMapper objectMapper = new ObjectMapper();
        UPS tracking = objectMapper.readValue(entity.getContent(), UPS.class);
        tracking.setStatus(tracking.getTrackResponse().getShipment().getPackage().getActivity().get(0).getStatus().getType());
        tracking.setCode(code);
        tracking.setUserCode(userCode);
        addPackage(tracking);
        return tracking;
    }
}
