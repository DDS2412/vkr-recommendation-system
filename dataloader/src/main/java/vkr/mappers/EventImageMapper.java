package vkr.mappers;

import org.mapstruct.Mapper;
import vkr.dtos.EventImageDto;
import vkr.mappers.basic.MappingConfig;
import vkr.mappers.basic.TwoWayMapper;
import vkr.models.EventImage;

@Mapper(config = MappingConfig.class)
public interface EventImageMapper extends TwoWayMapper<EventImageDto, EventImage> {
}
