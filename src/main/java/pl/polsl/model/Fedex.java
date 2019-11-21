package pl.polsl.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Fedex")
public class Fedex {

    @Id
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("code")
    private String code = null;

    @JsonProperty("userCode")
    private String userCode = null;

    @JsonProperty("size")
    private Integer size = null;

    @JsonProperty("CompletedTrackDetails")
    private Object CompletedTrackDetails = null;

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

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Object getCompletedTrackDetails() {
        return CompletedTrackDetails;
    }

    public void setCompletedTrackDetails(Object completedTrackDetails) {
        CompletedTrackDetails = completedTrackDetails;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
