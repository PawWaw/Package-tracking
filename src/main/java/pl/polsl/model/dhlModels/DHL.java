package pl.polsl.model.dhlModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document(collection = "DHL")
public class DHL {

    @Id
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("code")
    private String code = null;

    @JsonProperty("userCode")
    private String userCode = null;

    @JsonProperty("received_by")
    private String received_by = null;

    @JsonProperty("events")
    private List<DHLEvent> events = null;

    @JsonProperty("status")
    private String status = null;

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

    public String getReceived_by() {
        return received_by;
    }

    public void setReceived_by(String received_by) {
        this.received_by = received_by;
    }

    public List<DHLEvent> getEvents() {
        return events;
    }

    public void setEvents(List<DHLEvent> events) {
        this.events = events;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DHL dhl = (DHL) o;
        return Objects.equals(code, dhl.code) &&
                Objects.equals(userCode, dhl.userCode) &&
                Objects.equals(received_by, dhl.received_by) &&
                Objects.equals(events, dhl.events) &&
                Objects.equals(status, dhl.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, userCode, received_by, events, status);
    }
}
