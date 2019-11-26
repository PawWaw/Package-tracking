package pl.polsl.model.pocztaPolskaModels;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class PocztaPolskaDetails {

    @JsonProperty("datetime")
    private String datetime = null;

    @JsonProperty("place")
    private String place = null;

    @JsonProperty("event_code")
    private String event_code = null;

    @JsonProperty("endFlag")
    private String endFlag = null;

    @JsonProperty("description")
    private String description = null;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getEvent_code() {
        return event_code;
    }

    public void setEvent_code(String event_code) {
        this.event_code = event_code;
    }

    public String getEndFlag() {
        return endFlag;
    }

    public void setEndFlag(String endFlag) {
        this.endFlag = endFlag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PocztaPolskaDetails that = (PocztaPolskaDetails) o;
        return Objects.equals(datetime, that.datetime) &&
                Objects.equals(place, that.place) &&
                Objects.equals(event_code, that.event_code) &&
                Objects.equals(endFlag, that.endFlag) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(datetime, place, event_code, endFlag, description);
    }
}
