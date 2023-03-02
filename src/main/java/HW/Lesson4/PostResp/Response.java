package HW.Lesson4.PostResp;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "measures",
        "usages",
        "usageRecipeIds",
        "pantryItem",
        "aisle",
        "cost",
        "ingredientId"
})

public class Response {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("measures")
    private Measures measures;
    @JsonProperty("usages")
    private List<Object> usages = null;
    @JsonProperty("usageRecipeIds")
    private List<Object> usageRecipeIds = null;
    @JsonProperty("pantryItem")
    private Boolean pantryItem;
    @JsonProperty("aisle")
    private String aisle;
    @JsonProperty("cost")
    private Double cost;
    @JsonProperty("ingredientId")
    private Integer ingredientId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("measures")
    public Measures getMeasures() {
        return measures;
    }

    @JsonProperty("measures")
    public void setMeasures(Measures measures) {
        this.measures = measures;
    }

    @JsonProperty("usages")
    public List<Object> getUsages() {
        return usages;
    }

    @JsonProperty("usages")
    public void setUsages(List<Object> usages) {
        this.usages = usages;
    }

    @JsonProperty("usageRecipeIds")
    public List<Object> getUsageRecipeIds() {
        return usageRecipeIds;
    }

    @JsonProperty("usageRecipeIds")
    public void setUsageRecipeIds(List<Object> usageRecipeIds) {
        this.usageRecipeIds = usageRecipeIds;
    }

    @JsonProperty("pantryItem")
    public Boolean getPantryItem() {
        return pantryItem;
    }

    @JsonProperty("pantryItem")
    public void setPantryItem(Boolean pantryItem) {
        this.pantryItem = pantryItem;
    }

    @JsonProperty("aisle")
    public String getAisle() {
        return aisle;
    }

    @JsonProperty("aisle")
    public void setAisle(String aisle) {
        this.aisle = aisle;
    }

    @JsonProperty("cost")
    public Double getCost() {
        return cost;
    }

    @JsonProperty("cost")
    public void setCost(Double cost) {
        this.cost = cost;
    }

    @JsonProperty("ingredientId")
    public Integer getIngredientId() {
        return ingredientId;
    }

    @JsonProperty("ingredientId")
    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
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
            "original",
            "metric",
            "us"
    })

    public class Measures {

        @JsonProperty("original")
        private Original original;
        @JsonProperty("metric")
        private Metric metric;
        @JsonProperty("us")
        private Us us;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        @JsonProperty("original")
        public Original getOriginal() {
            return original;
        }

        @JsonProperty("original")
        public void setOriginal(Original original) {
            this.original = original;
        }

        @JsonProperty("metric")
        public Metric getMetric() {
            return metric;
        }

        @JsonProperty("metric")
        public void setMetric(Metric metric) {
            this.metric = metric;
        }

        @JsonProperty("us")
        public Us getUs() {
            return us;
        }

        @JsonProperty("us")
        public void setUs(Us us) {
            this.us = us;
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
                "amount",
                "unit"
        })

        public class Original {

            @JsonProperty("amount")
            private Double amount;
            @JsonProperty("unit")
            private String unit;
            @JsonIgnore
            private Map<String, Object> additionalProperties = new HashMap<String, Object>();

            @JsonProperty("amount")
            public Double getAmount() {
                return amount;
            }

            @JsonProperty("amount")
            public void setAmount(Double amount) {
                this.amount = amount;
            }

            @JsonProperty("unit")
            public String getUnit() {
                return unit;
            }

            @JsonProperty("unit")
            public void setUnit(String unit) {
                this.unit = unit;
            }

            @JsonAnyGetter
            public Map<String, Object> getAdditionalProperties() {
                return this.additionalProperties;
            }

            @JsonAnySetter
            public void setAdditionalProperty(String name, Object value) {
                this.additionalProperties.put(name, value);
            }


        }
    }
}
