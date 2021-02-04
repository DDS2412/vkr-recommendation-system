package vkr.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import vkr.configurations.EventConfigurationProperties;
import vkr.dtos.EventPlaceDto;
import vkr.dtos.EventPlacePageDto;
import vkr.enums.APIEnum;
import vkr.mappers.EventPlaceMapper;
import vkr.repositories.EventPlaceRepository;
import vkr.services.EventPlaceService;

import java.time.Instant;
import java.util.Objects;

@Service
public class EventPlaceServiceImpl implements EventPlaceService {
    private final EventPlaceMapper kudaGoEventPlaceMapper;
    private final EventPlaceRepository eventPlaceRepository;

    private final EventConfigurationProperties eventConfigurationProperties;

    private final WebClient webClient;

    private static final Logger logger = LogManager.getLogger(EventPlaceServiceImpl.class);

    public EventPlaceServiceImpl(EventPlaceMapper kudaGoEventPlaceMapper,
                                 EventPlaceRepository eventPlaceRepository,
                                 EventConfigurationProperties eventConfigurationProperties,
                                 WebClient webClient) {
        this.kudaGoEventPlaceMapper = kudaGoEventPlaceMapper;
        this.eventPlaceRepository = eventPlaceRepository;
        this.eventConfigurationProperties = eventConfigurationProperties;
        this.webClient = webClient;
    }

    @Override
    public void UpdatePlaces() {
        System.out.println("Обновление мест!");

        String requestPattern = String.format("%s?page_size=%d&location=%s&showing_since=%s&fields=%s",
                APIEnum.PLACE.getUrl(), eventConfigurationProperties.getPageSize(),
                eventConfigurationProperties.getEventLocation(), Instant.now().getEpochSecond(),
                eventConfigurationProperties.getEventPlaceFields());

        EventPlacePageDto currentEventPlacePage;

        long pageNumber = 1;

        do {
            currentEventPlacePage = getEventPlacePageByUrl(String.format("%s&page=%d",requestPattern, pageNumber++));

            for (EventPlaceDto placeDto : currentEventPlacePage.getResults()) {
                try {
                    if(!eventPlaceRepository.existsByKudaGoId(placeDto.getKudaGoId())){
                        eventPlaceRepository.save(kudaGoEventPlaceMapper.toEntity(placeDto));
                    }
                } catch (DataIntegrityViolationException ex) {
                    logger.error(ex);
                }
            }
        } while (currentEventPlacePage.getNextUrl() != null);

        System.out.println("Обновление мест завершено!");
    }

    private EventPlacePageDto getEventPlacePageByUrl(String url){
        return Objects.requireNonNull(webClient.get().uri(url).exchange().block()).bodyToMono(EventPlacePageDto.class).block();
    }
}
