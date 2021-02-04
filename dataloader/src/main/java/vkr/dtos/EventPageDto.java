package vkr.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class EventPageDto extends PageDto<EventDto> {
}
