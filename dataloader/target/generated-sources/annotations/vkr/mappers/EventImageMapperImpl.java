package vkr.mappers;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import vkr.dtos.EventImageDto;
import vkr.models.EventImage;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class EventImageMapperImpl implements EventImageMapper {

    @Override
    public EventImage toEntity(EventImageDto dto) {

        EventImage eventImage = new EventImage();

        if ( dto != null ) {
            eventImage.setImage( dto.getImage() );
        }

        return eventImage;
    }

    @Override
    public EventImage updateEntity(EventImage entity, EventImageDto dto) {

        if ( dto != null ) {
            if ( dto.getImage() != null ) {
                entity.setImage( dto.getImage() );
            }
        }

        return entity;
    }

    @Override
    public EventImageDto toDto(EventImage entity) {

        EventImageDto eventImageDto = new EventImageDto();

        if ( entity != null ) {
            eventImageDto.setImage( entity.getImage() );
        }

        return eventImageDto;
    }
}
