package pl.polsl.model;

import com.fasterxml.jackson.annotation.JsonProperty;

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
}
