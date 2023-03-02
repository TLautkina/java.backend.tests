package HW.Lesson4.PostResp;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "aisle",
        "items"
})
public class AddToShoppingListResponse {
    @JsonProperty("aisle")
    private String aisle;
    @JsonProperty("items")
    private List<Item> items = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("aisle")
    public String getAisle() {
        return aisle;
    }

    @JsonProperty("aisle")
    public void setAisle(String aisle) {
        this.aisle = aisle;
    }

    @JsonProperty("items")
    public String getItems() {
        return String.valueOf(items);
    }

    @JsonProperty("items")
    public void setItems(List<Item> items) {
        this.items = items;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "id",
            "name",
            "aisle",
    })

    private static class Item {
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("aisle")
        private String aisle;

        @JsonProperty("id")
        private Integer getId() {
            return id;
        }

        @JsonProperty("id")
        private void setId(Integer id) {
            this.id = id;
        }

        @JsonProperty("name")
        private String getName() {
            return name;
        }

        @JsonProperty("name")
        private void setName(String name) {
            this.name = name;
        }

        @JsonProperty("aisle")
        private String getAisle() {
            return aisle;
        }

        @JsonProperty("aisle")
        private void setAisle(String aisle) {
            this.aisle = aisle;
        }

    }

}

