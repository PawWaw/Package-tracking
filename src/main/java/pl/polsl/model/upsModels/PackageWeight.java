package pl.polsl.model.upsModels;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "UnitOfMeasurement",
    "Weight"
})
public class PackageWeight {

    @JsonProperty("UnitOfMeasurement")
    private UnitOfMeasurement unitOfMeasurement;
    @JsonProperty("Weight")
    private String weight;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("UnitOfMeasurement")
    public UnitOfMeasurement getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    @JsonProperty("UnitOfMeasurement")
    public void setUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    @JsonProperty("Weight")
    public String getWeight() {
        return weight;
    }

    @JsonProperty("Weight")
    public void setWeight(String weight) {
        this.weight = weight;
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
