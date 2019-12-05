package pl.polsl.model.fedexModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document(collection = "Fedex")
public class Fedex {

    @Id
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("code")
    private String code = null;

    @JsonProperty("userCode")
    private String userCode = null;

    @JsonProperty("size")
    private Integer size = null;

    @JsonProperty("CompletedTrackDetails")
    private List<CompletedTrackDetails> CompletedTrackDetails = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<CompletedTrackDetails> getCompletedTrackDetails() {
        return CompletedTrackDetails;
    }

    public void setCompletedTrackDetails(List<CompletedTrackDetails> completedTrackDetails) {
        CompletedTrackDetails = completedTrackDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fedex fedex = (Fedex) o;
        return Objects.equals(code, fedex.code) &&
                Objects.equals(userCode, fedex.userCode) &&
                Objects.equals(size, fedex.size) &&
                Objects.equals(CompletedTrackDetails, fedex.CompletedTrackDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, userCode, size, CompletedTrackDetails);
    }
}
