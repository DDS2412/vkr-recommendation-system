package vkr.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vkr.dtos.EventDto;
import vkr.dtos.EventImageDto;
import vkr.models.Event;
import vkr.models.EventImage;
import vkr.models.EventPlace;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class EventMapperImpl implements EventMapper {

    private final EventPlaceMapper eventPlaceMapper;
    private final EventImageMapper eventImageMapper;

    @Autowired
    public EventMapperImpl(EventPlaceMapper eventPlaceMapper, EventImageMapper eventImageMapper) {

        this.eventPlaceMapper = eventPlaceMapper;
        this.eventImageMapper = eventImageMapper;
    }

    @Override
    public Event toEntity(EventDto dto) {

        Event event = new Event();

        if ( dto != null ) {
            event.setKudaGoId( dto.getKudaGoId() );
            event.setTitle( dto.getTitle() );
            event.setShortTitle( dto.getShortTitle() );
            event.setPlace( eventPlaceMapper.toEntity( dto.getPlace() ) );
            event.setImages( eventImageDtoListToEventImageList( dto.getImages() ) );
            event.setDescription( dto.getDescription() );
            event.setSiteUrl( dto.getSiteUrl() );
            event.setFree( dto.isFree() );
        }

        return event;
    }

    @Override
    public Event updateEntity(Event entity, EventDto dto) {

        if ( dto != null ) {
            if ( dto.getKudaGoId() != null ) {
                entity.setKudaGoId( dto.getKudaGoId() );
            }
            if ( dto.getTitle() != null ) {
                entity.setTitle( dto.getTitle() );
            }
            if ( dto.getShortTitle() != null ) {
                entity.setShortTitle( dto.getShortTitle() );
            }
            if ( dto.getPlace() != null ) {
                if ( entity.getPlace() == null ) {
                    entity.setPlace( new EventPlace() );
                }
                eventPlaceMapper.updateEntity( entity.getPlace(), dto.getPlace() );
            }
            if ( entity.getImages() != null ) {
                List<EventImage> list = eventImageDtoListToEventImageList( dto.getImages() );
                if ( list != null ) {
                    entity.getImages().clear();
                    entity.getImages().addAll( list );
                }
            }
            else {
                List<EventImage> list = eventImageDtoListToEventImageList( dto.getImages() );
                if ( list != null ) {
                    entity.setImages( list );
                }
            }
            if ( dto.getDescription() != null ) {
                entity.setDescription( dto.getDescription() );
            }
            if ( dto.getSiteUrl() != null ) {
                entity.setSiteUrl( dto.getSiteUrl() );
            }
            entity.setFree( dto.isFree() );
        }

        return entity;
    }

    @Override
    public EventDto toDto(Event entity) {

        EventDto eventDto = new EventDto();

        if ( entity != null ) {
            eventDto.setKudaGoId( entity.getKudaGoId() );
            eventDto.setTitle( entity.getTitle() );
            eventDto.setShortTitle( entity.getShortTitle() );
            eventDto.setPlace( eventPlaceMapper.toDto( entity.getPlace() ) );
            eventDto.setDescription( entity.getDescription() );
            eventDto.setImages( eventImageListToEventImageDtoList( entity.getImages() ) );
            eventDto.setSiteUrl( entity.getSiteUrl() );
            eventDto.setFree( entity.isFree() );
        }

        return eventDto;
    }

    protected List<EventImage> eventImageDtoListToEventImageList(List<EventImageDto> list) {
        if ( list == null ) {
            return new ArrayList<EventImage>();
        }

        List<EventImage> list1 = new ArrayList<EventImage>( list.size() );
        for ( EventImageDto eventImageDto : list ) {
            list1.add( eventImageMapper.toEntity( eventImageDto ) );
        }

        return list1;
    }

    protected List<EventImageDto> eventImageListToEventImageDtoList(List<EventImage> list) {
        if ( list == null ) {
            return new ArrayList<EventImageDto>();
        }

        List<EventImageDto> list1 = new ArrayList<EventImageDto>( list.size() );
        for ( EventImage eventImage : list ) {
            list1.add( eventImageMapper.toDto( eventImage ) );
        }

        return list1;
    }
}
