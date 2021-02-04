package vkr.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import vkr.configurations.EventConfigurationProperties;
import vkr.dtos.EventDateDto;
import vkr.dtos.EventDto;
import vkr.dtos.EventPageDto;
import vkr.enums.APIEnum;
import vkr.mappers.EventMapper;
import vkr.models.Event;
import vkr.models.EventImage;
import vkr.repositories.EventCategoryRepository;
import vkr.repositories.EventImageRepository;
import vkr.repositories.EventPlaceRepository;
import vkr.repositories.EventRepository;
import vkr.services.EventService;
import vkr.services.FileCompressorService;

import java.time.Instant;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {
    private final FileCompressorService fileCompressorService;

    private final EventConfigurationProperties eventConfigurationProperties;

    private final EventMapper eventMapper;
    private final EventRepository eventRepository;
    private final EventCategoryRepository eventCategoryRepository;
    private final EventPlaceRepository eventPlaceRepository;
    private final EventImageRepository eventImageRepository;

    private final WebClient webClient;

    private static final Logger logger = LogManager.getLogger(EventServiceImpl.class);

    public EventServiceImpl(FileCompressorService fileCompressorService,
                            EventConfigurationProperties eventConfigurationProperties,
                            EventMapper eventMapper,
                            EventRepository eventRepository,
                            EventCategoryRepository eventCategoryRepository,
                            EventPlaceRepository eventPlaceRepository,
                            EventImageRepository eventImageRepository,
                            WebClient webClient) {
        this.fileCompressorService = fileCompressorService;
        this.eventConfigurationProperties = eventConfigurationProperties;
        this.eventMapper = eventMapper;
        this.eventRepository = eventRepository;
        this.eventCategoryRepository = eventCategoryRepository;
        this.eventPlaceRepository = eventPlaceRepository;
        this.eventImageRepository = eventImageRepository;
        this.webClient = webClient;
    }

    // TODO Возможно на KudaGo добавляются новые даты для ивентов, то есть следует проверять как ивенты, так и дату начала
    // TODO Добавить параллельную загрузку https://howtodoinjava.com/java/multi-threading/java-thread-pool-executor-example/
    @Override
    public void UpdateEvents() {
        System.out.println("Обновление ивентов!");
        String requestPattern = String.format("%s?page_size=%d&location=%s&actual_since=%d&categories=%s&fields=%s&text_format=%s&expand=%s",
                APIEnum.EVENT.getUrl(), eventConfigurationProperties.getPageSize(),
                eventConfigurationProperties.getEventLocation(), Instant.now().getEpochSecond(),
                eventConfigurationProperties.getCategories(), eventConfigurationProperties.getEventFields(),
                eventConfigurationProperties.getEventTextFormat(), eventConfigurationProperties.getEventExpand());

        EventPageDto currentEventPage;
        //long currentId = eventRepository.count();
        long pageNumber = 1;
        do {
            currentEventPage = getEventPageByUrl(String.format("%s&page=%d",requestPattern, pageNumber++));

            for (EventDto eventDto : currentEventPage.getResults()) {
                try {
                    Event event = eventMapper.toEntity(eventDto);

                    for(EventDateDto eventDateDto : eventDto.getDates()) {
                        if(!eventRepository.existsByKudaGoIdAndStartDate(event.getKudaGoId(), eventDateDto.getStartDate())){
                            Event clonedEvent = new Event(event);

                            clonedEvent = eventRepository.save(
                                    clonedEvent
                                            .setStartDate(eventDateDto.getStartDate())
                                            .setStartTime(eventDateDto.getStartTime())
                                            .setEndTime(eventDateDto.getEndTime())
                                            .setPlace(eventPlaceRepository.findFirstByKudaGoId(clonedEvent.getPlace().getKudaGoId()))
                                            .setCategories(eventDto.getCategorySlugs().stream().map(eventCategoryRepository::findFirstBySlug).collect(Collectors.toList())));

                            for (EventImage eventImage : clonedEvent.getImages()) {
                                eventImageRepository.save(eventImage
                                        .setCudagoImage(eventImage.getImage())
                                        .setImage(fileCompressorService.getCompressedImageFile(eventImage.getImage()))
                                        .setEvent(clonedEvent));
                            }
                        }
                    }
                } catch (DataIntegrityViolationException ex) {
                    logger.error(ex);
                    // TODO Добавить логгирование для обновления мест
                }
            }
        } while (currentEventPage.getNextUrl() != null);

        System.out.println("Обновление ивентов завершено!");
    }

    private EventPageDto getEventPageByUrl(String url){
        return Objects.requireNonNull(webClient.get().uri(url).exchange().block()).bodyToMono(EventPageDto.class).block();
    }
}
