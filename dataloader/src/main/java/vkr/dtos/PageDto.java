package vkr.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PageDto<Result> {
    private Long count;

    @JsonProperty("next")
    private String nextUrl;

    @JsonProperty("previous")
    private String previousUrl;

    private List<Result> results;
}
