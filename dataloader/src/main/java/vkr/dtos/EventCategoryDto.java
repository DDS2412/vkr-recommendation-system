package vkr.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

@Data
@Accessors(chain = true)
@FieldNameConstants
public class EventCategoryDto {
    @JsonProperty("id")
    private Integer kudaGoId;

    private String name;

    private String slug;

    @JsonIgnore
    @JsonProperty(value = "id")
    public Integer getKudaGoId() {return this.kudaGoId; }
}
