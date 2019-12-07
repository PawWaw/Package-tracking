package pl.polsl.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
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
import pl.polsl.model.upsModels.Status;
import pl.polsl.model.upsModels.StatusChanges;
import pl.polsl.model.upsModels.UPS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UPSService {

    @Autowired
    private UPSRepository repository;

    private void addPackage(UPS tracking) {

        UPS temp = repository.findByCode(tracking.getCode());
        if (temp == null)
            repository.save(tracking);
        else if (!temp.equals(tracking))
            tracking.setId(temp.getId());
        repository.save(tracking);
    }

    public List<StatusChanges> getAll(String userCode) {
        List<UPS> list = repository.findAll();
        List<StatusChanges> statusList = new ArrayList<>();

        for (UPS ups : list) {
            if (ups.getUserCode().equals(userCode)) {
                for(int j = 0; j < ups.getTrackResponse().getShipment().getPackage().getActivity().size(); j++) {
                    StatusChanges temp = new StatusChanges();
                    temp.setDate(ups.getTrackResponse().getShipment().getPackage().getActivity().get(j).getDate());
                    temp.setTime(ups.getTrackResponse().getShipment().getPackage().getActivity().get(j).getTime());
                    temp.setType(ups.getTrackResponse().getShipment().getPackage().getActivity().get(j).getStatus().getType());
                    temp.setDescription(ups.getTrackResponse().getShipment().getPackage().getActivity().get(j).getStatus().getDescription());
                    temp.setCode(ups.getTrackResponse().getShipment().getPackage().getActivity().get(j).getStatus().getCode());
                    temp.setCity(ups.getTrackResponse().getShipment().getPackage().getActivity().get(j).getActivityLocation().getAddress().getCity());
                    temp.setPackageCode(ups.getCode());
                    statusList.add(temp);
                }
            }
        }
        return statusList;
    }

    public List<StatusChanges> getPackage(String code, String userCode) throws IOException {

        UPS byCode = repository.findByCode(code);
        List<StatusChanges> list = new ArrayList<>();
        if (byCode != null)
            if (byCode.getStatus().equals("D")) {
                return getStatusChanges(list, byCode);
            }

        HttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost httppost = new HttpPost("https://wwwcie.ups.com/rest/Track");

        StringEntity params = new StringEntity("{\"UPSSecurity\":{\"UsernameToken\":{\"Username\": \"pawelwaw123\",\"Password\":\"last-legi0n\"},\"ServiceAccessToken\":{\"AccessLicenseNumber\":\"6D6FFE40C320D695\"}},\"TrackRequest\":{\"Request\":{\"RequestOption\":\"1\"},\"InquiryNumber\":\"" + code + "\"}} ");

        httppost.setEntity(params);

        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        UPS tracking = objectMapper.readValue(entity.getContent(), UPS.class);
        tracking.setStatus(tracking.getTrackResponse().getShipment().getPackage().getActivity().get(0).getStatus().getType());
        tracking.setCode(code);
        tracking.setUserCode(userCode);
        addPackage(tracking);

        return getStatusChanges(list, tracking);
    }

    private List<StatusChanges> getStatusChanges(List<StatusChanges> list, UPS tracking) {
        for (int i = 0; i < tracking.getTrackResponse().getShipment().getPackage().getActivity().size(); i++) {
            StatusChanges temp = new StatusChanges();
            temp.setCity(tracking.getTrackResponse().getShipment().getPackage().getActivity().get(i).getActivityLocation().getAddress().getCity());
            temp.setCode(tracking.getTrackResponse().getShipment().getPackage().getActivity().get(i).getStatus().getCode());
            temp.setDate(tracking.getTrackResponse().getShipment().getPackage().getActivity().get(i).getDate());
            temp.setDescription(tracking.getTrackResponse().getShipment().getPackage().getActivity().get(i).getStatus().getDescription());
            temp.setTime(tracking.getTrackResponse().getShipment().getPackage().getActivity().get(i).getTime());
            temp.setType(tracking.getTrackResponse().getShipment().getPackage().getActivity().get(i).getStatus().getType());
            temp.setPackageCode(tracking.getCode());
            list.add(temp);
        }
        return list;
    }

    public UPS getFullPackage(String code, String userCode) throws IOException {
        UPS byCode = repository.findByCode(code);
        List<StatusChanges> list = new ArrayList<>();
        if (byCode != null)
            if (byCode.getStatus().equals("D")) {
                return byCode;
            }

        HttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost httppost = new HttpPost("https://wwwcie.ups.com/rest/Track");

        StringEntity params = new StringEntity("{\"UPSSecurity\":{\"UsernameToken\":{\"Username\": \"pawelwaw123\",\"Password\":\"last-legi0n\"},\"ServiceAccessToken\":{\"AccessLicenseNumber\":\"6D6FFE40C320D695\"}},\"TrackRequest\":{\"Request\":{\"RequestOption\":\"1\"},\"InquiryNumber\":\"" + code + "\"}} ");

        httppost.setEntity(params);

        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        UPS tracking = objectMapper.readValue(entity.getContent(), UPS.class);
        tracking.setStatus(tracking.getTrackResponse().getShipment().getPackage().getActivity().get(0).getStatus().getType());
        tracking.setCode(code);
        tracking.setUserCode(userCode);
        addPackage(tracking);
        return tracking;
    }
}
