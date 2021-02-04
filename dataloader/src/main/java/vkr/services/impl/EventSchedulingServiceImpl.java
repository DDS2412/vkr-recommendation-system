package vkr.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import vkr.services.EventCategoryService;
import vkr.services.EventPlaceService;
import vkr.services.EventService;

import javax.annotation.PostConstruct;

@Service
public class EventSchedulingServiceImpl {
    private final EventService eventService;
    private final EventCategoryService eventCategoryService;
    private final EventPlaceService eventPlaceService;

    private static final Logger logger = LogManager.getLogger(EventSchedulingServiceImpl.class);

    public EventSchedulingServiceImpl(EventService eventService,
                                      EventCategoryService eventCategoryService,
                                      EventPlaceService eventPlaceService) {
        this.eventService = eventService;
        this.eventCategoryService = eventCategoryService;
        this.eventPlaceService = eventPlaceService;
    }

    @PostConstruct
    public void onStartup() {
        eventCategoryService.UpdateCategories();
        eventPlaceService.UpdatePlaces();
        eventService.UpdateEvents();
        logger.info("Update was successful!");
    }

    // Вызов методов каждую полночь
    @Scheduled(cron="0 0 0 * * ?")
    public void updateDataBase() {
        eventCategoryService.UpdateCategories();
        eventPlaceService.UpdatePlaces();
        eventService.UpdateEvents();
        logger.info("Update was successful!");
    }
}
