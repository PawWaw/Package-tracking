package pl.polsl.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Objects;

@Validated
@Document
public class Delivery {

    @Id
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("deliveryId")
    private String deliveryId = null;

    @JsonProperty("quantity")
    private Integer quantity = null;

    @JsonProperty("shipStartDate")
    private LocalDateTime shipStartDate = null;

    @JsonProperty("shipEndDate")
    private LocalDateTime shipEndDate = null;

    public Delivery() {}

    public Delivery(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Delivery(String deliveryId, Integer quantity, StatusEnum statusEnum, Boolean complete) {
        this.deliveryId = deliveryId;
        this.quantity = quantity;
        this.shipStartDate = LocalDateTime.now().minusDays(10);
        this.shipEndDate = LocalDateTime.now();
        this.status = statusEnum;
        this.complete = complete;
    }

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

    @JsonProperty("complete")
    private Boolean complete = false;

    public Delivery id(String id) {
        this.id = id;
        return this;
    }

    @ApiModelProperty(value = "")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Delivery deliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
        return this;
    }

    @ApiModelProperty(value = "")
    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Delivery quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    @ApiModelProperty(value = "")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Delivery shipStartDate(LocalDateTime shipStartDate) {
        this.shipStartDate = shipStartDate;
        return this;
    }

    @ApiModelProperty(value = "")
    @Valid
    public LocalDateTime getShipStartDate() {
        return shipStartDate;
    }

    public void setShipStartDate(LocalDateTime shipStartDate) {
        this.shipStartDate = shipStartDate;
    }

    public Delivery shipEndDate(LocalDateTime shipEndDate) {
        this.shipEndDate = shipEndDate;
        return this;
    }

    @ApiModelProperty(value = "")
    @Valid
    public LocalDateTime getShipEndDate() {
        return shipEndDate;
    }

    public void setShipEndDate(LocalDateTime shipEndDate) {
        this.shipEndDate = shipEndDate;
    }

    @ApiModelProperty(value = "Order Status")
    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Delivery complete(Boolean complete) {
        this.complete = complete;
        return this;
    }

    @ApiModelProperty(value = "")
    public Boolean isComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Delivery delivery = (Delivery) o;
        return Objects.equals(this.id, delivery.id) &&
                Objects.equals(this.deliveryId, delivery.deliveryId) &&
                Objects.equals(this.quantity, delivery.quantity) &&
                Objects.equals(this.shipStartDate, delivery.shipStartDate) &&
                Objects.equals(this.shipEndDate, delivery.shipEndDate) &&
                Objects.equals(this.status, delivery.status) &&
                Objects.equals(this.complete, delivery.complete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deliveryId, quantity, shipStartDate, shipEndDate, status, complete);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Delivery {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    deliveryId: ").append(toIndentedString(deliveryId)).append("\n");
        sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
        sb.append("    shipStartDate: ").append(toIndentedString(shipStartDate)).append("\n");
        sb.append("    shipEndDate: ").append(toIndentedString(shipEndDate)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    complete: ").append(toIndentedString(complete)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

