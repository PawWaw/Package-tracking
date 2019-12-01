package pl.polsl.model.upsModels;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Package {

    @JsonProperty("TrackingNumber")
    private String trackingNumber;

    @JsonProperty("Activity")
    private ArrayList<Activity> activity = null;

    @JsonProperty("PackageWeight")
    private PackageWeight packageWeight;

    @JsonProperty("ReferenceNumber")
    private List<ReferenceNumber_> referenceNumber = null;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("TrackingNumber")
    public String getTrackingNumber() {
        return trackingNumber;
    }

    @JsonProperty("TrackingNumber")
    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public ArrayList<Activity> getActivity() {
        return activity;
    }

    public void setActivity(ArrayList<Activity> activity) {
        this.activity = activity;
    }

    @JsonProperty("PackageWeight")
    public PackageWeight getPackageWeight() {
        return packageWeight;
    }

    @JsonProperty("PackageWeight")
    public void setPackageWeight(PackageWeight packageWeight) {
        this.packageWeight = packageWeight;
    }

    @JsonProperty("ReferenceNumber")
    public List<ReferenceNumber_> getReferenceNumber() {
        return referenceNumber;
    }

    @JsonProperty("ReferenceNumber")
    public void setReferenceNumber(List<ReferenceNumber_> referenceNumber) {
        this.referenceNumber = referenceNumber;
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
