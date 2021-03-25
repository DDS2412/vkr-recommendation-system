package vkr.mappers;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import vkr.dtos.EventPlaceDto;
import vkr.models.EventPlace;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class EventPlaceMapperImpl implements EventPlaceMapper {

    @Override
    public EventPlace toEntity(EventPlaceDto dto) {

        EventPlace eventPlace = new EventPlace();

        if ( dto != null ) {
            eventPlace.setKudaGoId( dto.getKudaGoId() );
            eventPlace.setTitle( dto.getTitle() );
            eventPlace.setAddress( dto.getAddress() );
            eventPlace.setShortTitle( dto.getShortTitle() );
            eventPlace.setLocation( dto.getLocation() );
            eventPlace.setSiteUrl( dto.getSiteUrl() );
            eventPlace.setSubway( dto.getSubway() );
        }

        return eventPlace;
    }

    @Override
    public EventPlace updateEntity(EventPlace entity, EventPlaceDto dto) {

        if ( dto != null ) {
            if ( dto.getKudaGoId() != null ) {
                entity.setKudaGoId( dto.getKudaGoId() );
            }
            if ( dto.getTitle() != null ) {
                entity.setTitle( dto.getTitle() );
            }
            if ( dto.getAddress() != null ) {
                entity.setAddress( dto.getAddress() );
            }
            if ( dto.getShortTitle() != null ) {
                entity.setShortTitle( dto.getShortTitle() );
            }
            if ( dto.getLocation() != null ) {
                entity.setLocation( dto.getLocation() );
            }
            if ( dto.getSiteUrl() != null ) {
                entity.setSiteUrl( dto.getSiteUrl() );
            }
            if ( dto.getSubway() != null ) {
                entity.setSubway( dto.getSubway() );
            }
        }

        return entity;
    }

    @Override
    public EventPlaceDto toDto(EventPlace entity) {

        EventPlaceDto eventPlaceDto = new EventPlaceDto();

        if ( entity != null ) {
            eventPlaceDto.setKudaGoId( entity.getKudaGoId() );
            eventPlaceDto.setTitle( entity.getTitle() );
            eventPlaceDto.setShortTitle( entity.getShortTitle() );
            eventPlaceDto.setAddress( entity.getAddress() );
            eventPlaceDto.setLocation( entity.getLocation() );
            eventPlaceDto.setSiteUrl( entity.getSiteUrl() );
            eventPlaceDto.setSubway( entity.getSubway() );
        }

        return eventPlaceDto;
    }
}
