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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Validated
@Document(collection = "Delivery")
public class Delivery {

    @Id
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("code")
    private String code = null;

    @JsonProperty("userId")
    private String userId = null;

    @JsonProperty("quantity")
    private Integer quantity = null;

    @JsonProperty("shipStartDate")
    private LocalDateTime shipStartDate = null;

    @JsonProperty("shipEndDate")
    private LocalDateTime shipEndDate = null;

    public Delivery() {
    }

    @JsonProperty("processingStage")
    private String processingStage = null;

    public Delivery(String code) {
        this.code = code;
    }

    public enum ActiveStatusEnum {
        AWAITING("awaiting"),

        PLACED("placed"),

        TRANSPORTED("transported"),

        DELIVERED("delivered");

        private String value;

        ActiveStatusEnum(String value) {
            this.value = value;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static ActiveStatusEnum fromValue(String text) {
            for (ActiveStatusEnum b : ActiveStatusEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    @JsonProperty("activeStatus")
    private ActiveStatusEnum activeStatus = null;

    @JsonProperty("history")
    @Valid
    private List<StatusHistory> history = null;

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

    public Delivery code(String code) {
        this.code = code;
        return this;
    }

    @ApiModelProperty(value = "")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Delivery processingStage(String processingStage) {
        this.processingStage = processingStage;
        return this;
    }

    /**
     * Get processingStage
     *
     * @return processingStage
     **/
    @ApiModelProperty(value = "")


    public String getProcessingStage() {
        return processingStage;
    }

    public void setProcessingStage(String processingStage) {
        this.processingStage = processingStage;
    }

    public Delivery activeStatus(ActiveStatusEnum activeStatus) {
        this.activeStatus = activeStatus;
        return this;
    }

    /**
     * Order Status
     *
     * @return activeStatus
     **/
    @ApiModelProperty(value = "Order Status")


    public ActiveStatusEnum getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(ActiveStatusEnum activeStatus) {
        this.activeStatus = activeStatus;
    }

    public Delivery history(List<StatusHistory> history) {
        this.history = history;
        return this;
    }

    public Delivery addHistoryItem(StatusHistory historyItem) {
        if (this.history == null) {
            this.history = new ArrayList<StatusHistory>();
        }
        this.history.add(historyItem);
        return this;
    }

    /**
     * Get history
     *
     * @return history
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<StatusHistory> getHistory() {
        return history;
    }

    public void setHistory(List<StatusHistory> history) {
        this.history = history;
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
                Objects.equals(this.code, delivery.code) &&
                Objects.equals(this.userId, delivery.userId) &&
                Objects.equals(this.quantity, delivery.quantity) &&
                Objects.equals(this.shipStartDate, delivery.shipStartDate) &&
                Objects.equals(this.shipEndDate, delivery.shipEndDate) &&
                Objects.equals(this.processingStage, delivery.processingStage) &&
                Objects.equals(this.activeStatus, delivery.activeStatus) &&
                Objects.equals(this.history, delivery.history) &&
                Objects.equals(this.complete, delivery.complete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, userId, quantity, shipStartDate, shipEndDate, processingStage, activeStatus, history, complete);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Delivery {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    code: ").append(toIndentedString(code)).append("\n");
        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
        sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
        sb.append("    shipStartDate: ").append(toIndentedString(shipStartDate)).append("\n");
        sb.append("    shipEndDate: ").append(toIndentedString(shipEndDate)).append("\n");
        sb.append("    processingStage: ").append(toIndentedString(processingStage)).append("\n");
        sb.append("    activeStatus: ").append(toIndentedString(activeStatus)).append("\n");
        sb.append("    history: ").append(toIndentedString(history)).append("\n");
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

