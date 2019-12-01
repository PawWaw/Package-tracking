package pl.polsl.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import pl.polsl.controller.InPostDetailsRepository;
import pl.polsl.controller.InPostRepository;
import pl.polsl.controller.InPostStatusesRepository;
import pl.polsl.model.inPostModels.InPost;
import pl.polsl.model.inPostModels.InPostDataList;
import pl.polsl.model.inPostModels.InPostDetails;
import pl.polsl.model.inPostModels.InPostStatuses;

import java.util.ArrayList;
import java.util.List;

@Service
public class InPostService {

    @Autowired
    private InPostRepository repository;

    @Autowired
    private InPostDetailsRepository details;

    @Autowired
    private InPostStatusesRepository statusesRepo;

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

    public List<InPostDetails> getAll(String usercode) {
        List<InPostStatuses> tempoo = statusesRepo.findAll();
        List<InPostDataList> temp = tempoo.get(0).getItems();
        List<InPostDetails> detailsList = new ArrayList<>();
        List<InPost> tempList = repository.findAll();

        for (InPost inPost : tempList) {
            if (inPost.getUserCode().equals(usercode)) {
                for (int j = 0; j < inPost.getTracking_details().size(); j++) {
                    InPostDetails tempDetails = new InPostDetails();
                    tempDetails.setCode(inPost.getCode());
                    tempDetails.setAgency(inPost.getTracking_details().get(j).getAgency());
                    tempDetails.setDatetime(inPost.getTracking_details().get(j).getDatetime());
                    tempDetails.setOrigin_status(inPost.getTracking_details().get(j).getOrigin_status());
                    for (int k = 0; k < temp.size(); k++) {
                        if (temp.get(k).getName().equals(inPost.getTracking_details().get(j).getStatus())) {
                            tempDetails.setStatus(temp.get(k).getDescription());
                        }
                    }
                    detailsList.add(tempDetails);
                }
            }
        }


        return detailsList;
    }
}
