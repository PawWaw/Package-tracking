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
    "InquiryNumber",
    "ShipmentType",
    "ShipperNumber",
    "Service",
    "ReferenceNumber",
    "PickupDate",
    "Package"
})
public class Shipment {

    @JsonProperty("InquiryNumber")
    private InquiryNumber inquiryNumber;
    @JsonProperty("ShipmentType")
    private ShipmentType shipmentType;
    @JsonProperty("ShipperNumber")
    private String shipperNumber;
    @JsonProperty("Service")
    private Service service;
    @JsonProperty("ReferenceNumber")
    private ReferenceNumber referenceNumber;
    @JsonProperty("PickupDate")
    private String pickupDate;
    @JsonProperty("Package")
    private Package _package;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("InquiryNumber")
    public InquiryNumber getInquiryNumber() {
        return inquiryNumber;
    }

    @JsonProperty("InquiryNumber")
    public void setInquiryNumber(InquiryNumber inquiryNumber) {
        this.inquiryNumber = inquiryNumber;
    }

    @JsonProperty("ShipmentType")
    public ShipmentType getShipmentType() {
        return shipmentType;
    }

    @JsonProperty("ShipmentType")
    public void setShipmentType(ShipmentType shipmentType) {
        this.shipmentType = shipmentType;
    }

    @JsonProperty("ShipperNumber")
    public String getShipperNumber() {
        return shipperNumber;
    }

    @JsonProperty("ShipperNumber")
    public void setShipperNumber(String shipperNumber) {
        this.shipperNumber = shipperNumber;
    }

    @JsonProperty("Service")
    public Service getService() {
        return service;
    }

    @JsonProperty("Service")
    public void setService(Service service) {
        this.service = service;
    }

    @JsonProperty("ReferenceNumber")
    public ReferenceNumber getReferenceNumber() {
        return referenceNumber;
    }

    @JsonProperty("ReferenceNumber")
    public void setReferenceNumber(ReferenceNumber referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    @JsonProperty("PickupDate")
    public String getPickupDate() {
        return pickupDate;
    }

    @JsonProperty("PickupDate")
    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    @JsonProperty("Package")
    public Package getPackage() {
        return _package;
    }

    @JsonProperty("Package")
    public void setPackage(Package _package) {
        this._package = _package;
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
