package pl.polsl.model.inPostModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document(collection = "InPost")
public class InPost {

    @Id
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("code")
    private String code = null;

    @JsonProperty("updated_at")
    private String updated_at = null;

    @JsonProperty("service")
    private String service = null;

    @JsonProperty("expected_flow")
    private List<Object> expected_flow = null;

    @JsonProperty("tracking_number")
    private String tracking_number = null;

    @JsonProperty("created_at")
    private String created_at = null;

    @JsonProperty("tracking_details")
    private List<InPostDetails> tracking_details = null;

    @JsonProperty("type")
    private String type = null;

    @JsonProperty("status")
    private String status = null;

    @JsonProperty("custom_attributes")
    private Object custom_attributes = null;

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

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public List<Object> getExpected_flow() {
        return expected_flow;
    }

    public void setExpected_flow(List<Object> expected_flow) {
        this.expected_flow = expected_flow;
    }

    public String getTracking_number() {
        return tracking_number;
    }

    public void setTracking_number(String tracking_number) {
        this.tracking_number = tracking_number;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public List<InPostDetails> getTracking_details() {
        return tracking_details;
    }

    public void setTracking_details(List<InPostDetails> tracking_details) {
        this.tracking_details = tracking_details;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getCustom_attributes() {
        return custom_attributes;
    }

    public void setCustom_attributes(Object custom_attributes) {
        this.custom_attributes = custom_attributes;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InPost inPost = (InPost) o;
        return Objects.equals(code, inPost.code) &&
                Objects.equals(updated_at, inPost.updated_at) &&
                Objects.equals(service, inPost.service) &&
                Objects.equals(expected_flow, inPost.expected_flow) &&
                Objects.equals(tracking_number, inPost.tracking_number) &&
                Objects.equals(created_at, inPost.created_at) &&
                Objects.equals(tracking_details, inPost.tracking_details) &&
                Objects.equals(type, inPost.type) &&
                Objects.equals(status, inPost.status) &&
                Objects.equals(custom_attributes, inPost.custom_attributes) &&
                Objects.equals(userCode, inPost.userCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, updated_at, service, expected_flow, tracking_number, created_at, tracking_details, type, status, custom_attributes, userCode);
    }
}
