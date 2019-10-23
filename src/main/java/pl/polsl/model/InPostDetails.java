package pl.polsl.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class InPostDetails {

    @JsonProperty("status")
    private String status = null;

    @JsonProperty("origin_status")
    private String origin_status = null;

    @JsonProperty("agency")
    private String agency = null;

    @JsonProperty("datetime")
    private String datetime = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrigin_status() {
        return origin_status;
    }

    public void setOrigin_status(String origin_status) {
        this.origin_status = origin_status;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
