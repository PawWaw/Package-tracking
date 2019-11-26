package pl.polsl.model.fedexModels;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class FedexAddress {

    @JsonProperty("City")
    private String city = null;

    @JsonProperty("StateOrProvinceCode")
    private String stateOrProvinceCode = null;

    @JsonProperty("CountryCode")
    private String countryCode = null;

    @JsonProperty("CountryName")
    private String countryName = null;

    @JsonProperty("Residential")
    private String residential = null;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateOrProvinceCode() {
        return stateOrProvinceCode;
    }

    public void setStateOrProvinceCode(String stateOrProvinceCode) {
        this.stateOrProvinceCode = stateOrProvinceCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getResidential() {
        return residential;
    }

    public void setResidential(String residential) {
        this.residential = residential;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FedexAddress that = (FedexAddress) o;
        return Objects.equals(city, that.city) &&
                Objects.equals(stateOrProvinceCode, that.stateOrProvinceCode) &&
                Objects.equals(countryCode, that.countryCode) &&
                Objects.equals(countryName, that.countryName) &&
                Objects.equals(residential, that.residential);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, stateOrProvinceCode, countryCode, countryName, residential);
    }
}
