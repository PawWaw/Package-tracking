package pl.polsl.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "PocztaPolska")
public class PocztaPolska {

    @Id
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("code")
    private String code = null;

    @JsonProperty("sendCountry")
    private String sendCountry = null;

    @JsonProperty("arrivalCountry")
    private String arrivalCountry = null;

    @JsonProperty("sendDate")
    private String sendDate = null;

    @JsonProperty("packageType")
    private String packageType = null;

    @JsonProperty("sendPostOffice")
    private String sendPostOffice = null;

    @JsonProperty("arrivalPostOffice")
    private String arrivalPostOffice = null;

    @JsonProperty("deliveredFlag")
    private String deliveredFlag = null;

    @JsonProperty("events")
    private List<PocztaPolskaDetails> events = null;

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

    public String getSendCountry() {
        return sendCountry;
    }

    public void setSendCountry(String sendCountry) {
        this.sendCountry = sendCountry;
    }

    public String getArrivalCountry() {
        return arrivalCountry;
    }

    public void setArrivalCountry(String arrivalCountry) {
        this.arrivalCountry = arrivalCountry;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getSendPostOffice() {
        return sendPostOffice;
    }

    public void setSendPostOffice(String sendPostOffice) {
        this.sendPostOffice = sendPostOffice;
    }

    public String getArrivalPostOffice() {
        return arrivalPostOffice;
    }

    public void setArrivalPostOffice(String arrivalPostOffice) {
        this.arrivalPostOffice = arrivalPostOffice;
    }

    public String getDeliveredFlag() {
        return deliveredFlag;
    }

    public void setDeliveredFlag(String deliveredFlag) {
        this.deliveredFlag = deliveredFlag;
    }

    public List<PocztaPolskaDetails> getEvents() {
        return events;
    }

    public void setEvents(List<PocztaPolskaDetails> events) {
        this.events = events;
    }
}
