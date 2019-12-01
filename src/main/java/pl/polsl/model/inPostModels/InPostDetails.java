package pl.polsl.model.inPostModels;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Objects;

public class InPostDetails {

    @JsonProperty("code")
    private String code = null;

    @JsonProperty("status")
    private String status = null;

    @JsonProperty("origin_status")
    private String origin_status = null;

    @JsonProperty("agency")
    private String agency = null;

    @JsonProperty("datetime")
    private String datetime = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InPostDetails that = (InPostDetails) o;
        return Objects.equals(status, that.status) &&
                Objects.equals(origin_status, that.origin_status) &&
                Objects.equals(agency, that.agency) &&
                Objects.equals(datetime, that.datetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, origin_status, agency, datetime);
    }
}
