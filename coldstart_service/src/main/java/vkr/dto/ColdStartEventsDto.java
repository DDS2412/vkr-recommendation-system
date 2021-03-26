package vkr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@AllArgsConstructor
public class ColdStartEventsDto {
    private List<ColdStartEventInfoDto> coldStartEventInfoDtos;

    private Integer eventsPerPage;

    private Integer nPages;
}
