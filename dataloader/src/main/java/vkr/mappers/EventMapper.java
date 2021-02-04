package vkr.mappers;

import org.mapstruct.Mapper;
import vkr.dtos.EventDto;
import vkr.mappers.basic.MappingConfig;
import vkr.mappers.basic.TwoWayMapper;
import vkr.models.Event;

@Mapper(uses = { EventPlaceMapper.class, EventCategoryMapper.class, EventImageMapper.class }, config = MappingConfig.class)
public interface EventMapper extends TwoWayMapper<EventDto, Event> {
}
