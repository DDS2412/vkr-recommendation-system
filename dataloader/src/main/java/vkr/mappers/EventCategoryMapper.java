package vkr.mappers;

import org.mapstruct.Mapper;
import vkr.dtos.EventCategoryDto;
import vkr.mappers.basic.MappingConfig;
import vkr.mappers.basic.TwoWayMapper;
import vkr.models.EventCategory;

@Mapper(config = MappingConfig.class)
public interface EventCategoryMapper extends TwoWayMapper<EventCategoryDto, EventCategory> {
}
