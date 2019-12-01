package pl.polsl.model.dhlModels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DHLDetail extends DHLEvent {

    @JsonProperty("code")
    private String code = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
