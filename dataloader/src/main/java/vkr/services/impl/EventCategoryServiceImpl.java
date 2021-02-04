package vkr.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import vkr.dtos.EventCategoryDto;
import vkr.enums.APIEnum;
import vkr.mappers.EventCategoryMapper;
import vkr.repositories.EventCategoryRepository;
import vkr.services.EventCategoryService;

import java.util.Objects;

@Service
public class EventCategoryServiceImpl implements EventCategoryService {
    private static final Logger logger = LogManager.getLogger(EventCategoryServiceImpl.class);

    private final EventCategoryMapper eventCategoryMapper;

    private final EventCategoryRepository eventCategoryRepository;

    private final WebClient webClient;

    public EventCategoryServiceImpl(EventCategoryMapper eventCategoryMapper,
                                    EventCategoryRepository eventCategoryRepository,
                                    WebClient webClient) {
        this.eventCategoryMapper = eventCategoryMapper;
        this.eventCategoryRepository = eventCategoryRepository;
        this.webClient = webClient;
    }

    @Override
    public void UpdateCategories(){
        System.out.println("Обновление категорий!");
        EventCategoryDto[] categoryDtos = Objects.requireNonNull(
                webClient
                        .get()
                        .uri(APIEnum.EVENT_CATEGORIES.getUrl())
                        .exchange()
                        .block())
                .bodyToMono(EventCategoryDto[].class)
                .block();

        for(EventCategoryDto categoryDto : Objects.requireNonNull(categoryDtos)) {
            try {
                if(!eventCategoryRepository.existsByKudaGoId(categoryDto.getKudaGoId())){
                    eventCategoryRepository.save(eventCategoryMapper.toEntity(categoryDto));
                }
            } catch (DataIntegrityViolationException ex) {
                logger.error(ex);
            }
        }
        System.out.println("Обновление категорий завершено!");
    }
}
