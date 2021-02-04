package vkr.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class EventDto {
    @JsonProperty("id")
    private Long kudaGoId;

    private String title;

    @JsonProperty("short_title")
    private String shortTitle;

    private EventPlaceDto place;

    @JsonProperty("categories")
    private List<String> categorySlugs = new ArrayList<>();

    @JsonProperty("dates")
    private List<EventDateDto> dates = new ArrayList<>();

    private String description;

    private List<EventImageDto> images;

    @JsonProperty("site_url")
    private String siteUrl;

    @JsonProperty("is_free")
    private boolean isFree;
}
