package pl.polsl.model.dhlModels;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class DHLEvent {

    @JsonProperty("status")
    private String status = null;

    @JsonProperty("description")
    private String description = null;

    @JsonProperty("terminal")
    private String terminal = null;

    @JsonProperty("timestamp")
    private String timestamp = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DHLEvent dhlEvent = (DHLEvent) o;
        return Objects.equals(status, dhlEvent.status) &&
                Objects.equals(description, dhlEvent.description) &&
                Objects.equals(terminal, dhlEvent.terminal) &&
                Objects.equals(timestamp, dhlEvent.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, description, terminal, timestamp);
    }
}
