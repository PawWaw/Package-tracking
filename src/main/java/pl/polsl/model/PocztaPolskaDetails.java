package pl.polsl.model;

import com.fasterxml.jackson.annotation.JsonProperty;

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
}
