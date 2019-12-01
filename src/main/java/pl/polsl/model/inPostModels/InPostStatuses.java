package pl.polsl.model.inPostModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "InPostStatuses")
public class InPostStatuses {

    @JsonProperty("href")
    private String href = null;

    @JsonProperty("items")
    private List<InPostDataList> items = null;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<InPostDataList> getItems() {
        return items;
    }

    public void setItems(List<InPostDataList> items) {
        this.items = items;
    }
}
