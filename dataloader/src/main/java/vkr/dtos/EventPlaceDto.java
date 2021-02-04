package vkr.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class EventPlaceDto {
    @JsonProperty("id")
    private Long kudaGoId;

    private String title;

    @JsonProperty("short_title")
    private String shortTitle;

    private String address;

    private String location;

    @JsonProperty("site_url")
    private String siteUrl;

    private String subway;
}
