package pl.polsl.model.fedexModels;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class FedexDates {

    @JsonProperty("type")
    private String type = null;

    @JsonProperty("date")
    private String date = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FedexDates that = (FedexDates) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, date);
    }
}
