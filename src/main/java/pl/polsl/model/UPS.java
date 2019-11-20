package pl.polsl.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UPS")
public class UPS {

    @Id
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("code")
    private String code = null;

    @JsonProperty("status")
    private String status = null;

    @JsonProperty("TrackResponse")
    private Object trackResponse = null;

    @JsonProperty("userCode")
    private String userCode = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getTrackResponse() {
        return trackResponse;
    }

    public void setTrackResponse(Object trackResponse) {
        this.trackResponse = trackResponse;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
