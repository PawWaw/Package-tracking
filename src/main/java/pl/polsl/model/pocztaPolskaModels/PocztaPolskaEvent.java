package pl.polsl.model.pocztaPolskaModels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PocztaPolskaEvent extends PocztaPolskaDetails {

    @JsonProperty("code")
    private String code= null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
