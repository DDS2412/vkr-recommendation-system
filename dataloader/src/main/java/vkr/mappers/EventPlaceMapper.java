package vkr.mappers;

import org.mapstruct.Mapper;
import vkr.dtos.EventPlaceDto;
import vkr.mappers.basic.MappingConfig;
import vkr.mappers.basic.TwoWayMapper;
import vkr.models.EventPlace;

@Mapper(config = MappingConfig.class)
public interface EventPlaceMapper extends TwoWayMapper<EventPlaceDto, EventPlace> {
}
