package pl.polsl.model.fedexModels;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class CompletedTrackDetails {

    @JsonProperty("TrackingNumber")
    private String trackingNumber = null;

    @JsonProperty("TrackingNumberUniqueIdentifier")
    private String trackingNumberUniqueIdentifier = null;

    @JsonProperty("CarrierCode")
    private String carrierCode = null;

    @JsonProperty("OperatingCompanyOrCarrierDescription")
    private String operatingCompanyOrCarrierDescription = null;

    @JsonProperty("PackageSequenceNumber")
    private String packageSequenceNumber = null;

    @JsonProperty("PackageCount")
    private String packageCount = null;

    @JsonProperty("datesOrTimes")
    private List<datesOrTimes> datesOrTimes = null;

    @JsonProperty("DestinationAddress")
    private FedexAddress destinationAddress = null;

    @JsonProperty("DeliveryAttempts")
    private String deliveryAttempts = null;

    @JsonProperty("TotalUniqueAddressCountInConsolidation")
    private String totalUniqueAddressCountInConsolidation = null;

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getTrackingNumberUniqueIdentifier() {
        return trackingNumberUniqueIdentifier;
    }

    public void setTrackingNumberUniqueIdentifier(String trackingNumberUniqueIdentifier) {
        this.trackingNumberUniqueIdentifier = trackingNumberUniqueIdentifier;
    }

    public String getCarrierCode() {
        return carrierCode;
    }

    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    public String getOperatingCompanyOrCarrierDescription() {
        return operatingCompanyOrCarrierDescription;
    }

    public void setOperatingCompanyOrCarrierDescription(String operatingCompanyOrCarrierDescription) {
        this.operatingCompanyOrCarrierDescription = operatingCompanyOrCarrierDescription;
    }

    public String getPackageSequenceNumber() {
        return packageSequenceNumber;
    }

    public void setPackageSequenceNumber(String packageSequenceNumber) {
        this.packageSequenceNumber = packageSequenceNumber;
    }

    public String getPackageCount() {
        return packageCount;
    }

    public void setPackageCount(String packageCount) {
        this.packageCount = packageCount;
    }

    public List<pl.polsl.model.fedexModels.datesOrTimes> getDatesOrTimes() {
        return datesOrTimes;
    }

    public void setDatesOrTimes(List<pl.polsl.model.fedexModels.datesOrTimes> datesOrTimes) {
        this.datesOrTimes = datesOrTimes;
    }

    public FedexAddress getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(FedexAddress destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getDeliveryAttempts() {
        return deliveryAttempts;
    }

    public void setDeliveryAttempts(String deliveryAttempts) {
        this.deliveryAttempts = deliveryAttempts;
    }

    public String getTotalUniqueAddressCountInConsolidation() {
        return totalUniqueAddressCountInConsolidation;
    }

    public void setTotalUniqueAddressCountInConsolidation(String totalUniqueAddressCountInConsolidation) {
        this.totalUniqueAddressCountInConsolidation = totalUniqueAddressCountInConsolidation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompletedTrackDetails that = (CompletedTrackDetails) o;
        return Objects.equals(trackingNumber, that.trackingNumber) &&
                Objects.equals(trackingNumberUniqueIdentifier, that.trackingNumberUniqueIdentifier) &&
                Objects.equals(carrierCode, that.carrierCode) &&
                Objects.equals(operatingCompanyOrCarrierDescription, that.operatingCompanyOrCarrierDescription) &&
                Objects.equals(packageSequenceNumber, that.packageSequenceNumber) &&
                Objects.equals(packageCount, that.packageCount) &&
                Objects.equals(datesOrTimes, that.datesOrTimes) &&
                Objects.equals(destinationAddress, that.destinationAddress) &&
                Objects.equals(deliveryAttempts, that.deliveryAttempts) &&
                Objects.equals(totalUniqueAddressCountInConsolidation, that.totalUniqueAddressCountInConsolidation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trackingNumber, trackingNumberUniqueIdentifier, carrierCode, operatingCompanyOrCarrierDescription, packageSequenceNumber, packageCount, datesOrTimes, destinationAddress, deliveryAttempts, totalUniqueAddressCountInConsolidation);
    }
}
