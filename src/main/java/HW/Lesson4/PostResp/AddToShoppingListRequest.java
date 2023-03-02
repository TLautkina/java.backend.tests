package HW.Lesson4.PostResp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "item",
        "aisle",
        "parse"
})

public class AddToShoppingListRequest {
    @JsonProperty("item")
    private String item;
    @JsonProperty("aisle")
    private String aisle;
    @JsonProperty("parse")
    private Boolean parse;

    public AddToShoppingListRequest(String item, String aisle, Boolean parse) {
        this.item = item;
        this.aisle = aisle;
        this.parse = parse;
    }

    public AddToShoppingListRequest() {

    }

    @JsonProperty("item")
    public String getItem() {
        return item;
    }

    @JsonProperty("item")
    public void setItem(String item) {
        this.item = item;
    }

    @JsonProperty("aisle")
    public String getAisle() {
        return aisle;
    }

    @JsonProperty("aisle")
    public void setAisle(String aisle) {
        this.aisle = aisle;
    }

    @JsonProperty("parse")
    public Boolean getParse() {
        return parse;
    }

    @JsonProperty("parse")
    public void setParse(Boolean parse) {
        this.parse = parse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddToShoppingListRequest request = (AddToShoppingListRequest) o;
        return Objects.equals(item, request.item) && Objects.equals(aisle, request.aisle) && Objects.equals(parse, request.parse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, aisle, parse);
    }

    @Override
    public String toString() {
        return "AddToShoppingListRequest{" +
                "item='" + item + '\'' +
                ", aisle='" + aisle + '\'' +
                ", parse=" + parse +
                '}';
    }
}