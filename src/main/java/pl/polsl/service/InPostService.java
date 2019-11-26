package pl.polsl.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import pl.polsl.controller.InPostRepository;
import pl.polsl.model.inPostModels.InPost;

import java.util.List;

@Service
public class InPostService {

    @Autowired
    private InPostRepository repository;

    private void addPackage(InPost tracking) {
        InPost temp = repository.findByCode(tracking.getCode());
        if(temp == null)
            repository.save(tracking);
        else if (!temp.equals(tracking))
        {
            tracking.setId(temp.getId());
            repository.save(tracking);
        }
    }

    public InPost getPackage(String code, String userCode) throws Exception {

        final InPost byCode = repository.findByCode(code);
        if(byCode != null)
            if(byCode.getStatus().equals("delivered"))
                return byCode;

        CloseableHttpClient httpClient =  HttpClients.createDefault();
        String getCategoriesUrl = "https://api-shipx-pl.easypack24.net/v1/tracking/" + code;

        HttpGet request = new HttpGet(getCategoriesUrl);
        HttpHeaders header = new HttpHeaders();

        String content_type = "application/json";
        request.addHeader(HttpHeaders.CONTENT_TYPE, content_type);

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            HttpEntity entity = response.getEntity();
            ObjectMapper objectMapper = new ObjectMapper();
            InPost tracking = objectMapper.readValue(entity.getContent(), InPost.class);
            tracking.setCode(tracking.getTracking_number());
            tracking.setUserCode(userCode);
            Header headers = entity.getContentType();
            addPackage(tracking);

            return tracking;
        }
    }

    public List<InPost> getAll() {
        return repository.findAll();
    }
}
