package vkr.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class EventPlacePageDto extends PageDto<EventPlaceDto> {
}
