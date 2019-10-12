package pl.polsl.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Objects;

@Validated
public class StatusHistory {

    public enum StatusEnum {
        AWAITING("awaiting"),

        PLACED("placed"),

        TRANSPORTED("transported"),

        DELIVERED("delivered");

        private String value;

        StatusEnum(String value) {
            this.value = value;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static StatusEnum fromValue(String text) {
            for (StatusEnum b : StatusEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    @JsonProperty("status")
    private StatusEnum status = null;

    @JsonProperty("date")
    private LocalDateTime date = null;

    @JsonProperty("localization")
    private String localization = null;

    public StatusHistory status(StatusEnum status) {
        this.status = status;
        return this;
    }

    @ApiModelProperty(value = "")
    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public StatusHistory date(LocalDateTime date) {
        this.date = date;
        return this;
    }

    @ApiModelProperty(value = "")
    @Valid
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public StatusHistory localization(String localization) {
        this.localization = localization;
        return this;
    }

    @ApiModelProperty(value = "")
    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StatusHistory statusHistory = (StatusHistory) o;
        return Objects.equals(this.status, statusHistory.status) &&
                Objects.equals(this.date, statusHistory.date) &&
                Objects.equals(this.localization, statusHistory.localization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, date, localization);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class StatusHistory {\n");

        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    date: ").append(toIndentedString(date)).append("\n");
        sb.append("    localization: ").append(toIndentedString(localization)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

