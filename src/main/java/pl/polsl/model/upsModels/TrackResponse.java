package pl.polsl.model.upsModels;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class TrackResponse {

    @JsonProperty("Response")
    private Response response;
    @JsonProperty("Shipment")
    private Shipment shipment;
    @JsonProperty("Disclaimer")
    private String disclaimer;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Response")
    public Response getResponse() {
        return response;
    }

    @JsonProperty("Response")
    public void setResponse(Response response) {
        this.response = response;
    }

    @JsonProperty("Shipment")
    public Shipment getShipment() {
        return shipment;
    }

    @JsonProperty("Shipment")
    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    @JsonProperty("Disclaimer")
    public String getDisclaimer() {
        return disclaimer;
    }

    @JsonProperty("Disclaimer")
    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
