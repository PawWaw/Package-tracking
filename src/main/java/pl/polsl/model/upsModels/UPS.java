package pl.polsl.model.upsModels;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "code",
    "status",
    "trackResponse",
    "userCode",
    "_class"
})
@Document(collection = "UPS")
public class UPS {

    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("code")
    private String code;

    @JsonProperty("status")
    private String status;

    @JsonProperty("TrackResponse")
    private TrackResponse trackResponse;

    @JsonProperty("userCode")
    private String userCode;

    @JsonProperty("_class")
    private String _class;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("trackResponse")
    public TrackResponse getTrackResponse() {
        return trackResponse;
    }

    @JsonProperty("trackResponse")
    public void setTrackResponse(TrackResponse trackResponse) {
        this.trackResponse = trackResponse;
    }

    @JsonProperty("userCode")
    public String getUserCode() {
        return userCode;
    }

    @JsonProperty("userCode")
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    @JsonProperty("_class")
    public String getClass_() {
        return _class;
    }

    @JsonProperty("_class")
    public void setClass_(String _class) {
        this._class = _class;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UPS ups = (UPS) o;
        return Objects.equals(code, ups.code) &&
                Objects.equals(status, ups.status) &&
                Objects.equals(trackResponse, ups.trackResponse) &&
                Objects.equals(userCode, ups.userCode) &&
                Objects.equals(additionalProperties, ups.additionalProperties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, status, trackResponse, userCode, _class, additionalProperties);
    }
}
