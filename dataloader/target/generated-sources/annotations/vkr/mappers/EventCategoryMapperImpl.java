package vkr.mappers;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import vkr.dtos.EventCategoryDto;
import vkr.models.EventCategory;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class EventCategoryMapperImpl implements EventCategoryMapper {

    @Override
    public EventCategory toEntity(EventCategoryDto dto) {

        EventCategory eventCategory = new EventCategory();

        if ( dto != null ) {
            eventCategory.setKudaGoId( dto.getKudaGoId() );
            eventCategory.setName( dto.getName() );
            eventCategory.setSlug( dto.getSlug() );
        }

        return eventCategory;
    }

    @Override
    public EventCategory updateEntity(EventCategory entity, EventCategoryDto dto) {

        if ( dto != null ) {
            if ( dto.getKudaGoId() != null ) {
                entity.setKudaGoId( dto.getKudaGoId() );
            }
            if ( dto.getName() != null ) {
                entity.setName( dto.getName() );
            }
            if ( dto.getSlug() != null ) {
                entity.setSlug( dto.getSlug() );
            }
        }

        return entity;
    }

    @Override
    public EventCategoryDto toDto(EventCategory entity) {

        EventCategoryDto eventCategoryDto = new EventCategoryDto();

        if ( entity != null ) {
            eventCategoryDto.setKudaGoId( entity.getKudaGoId() );
            eventCategoryDto.setName( entity.getName() );
            eventCategoryDto.setSlug( entity.getSlug() );
        }

        return eventCategoryDto;
    }
}
