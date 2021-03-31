package vkr.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import vkr.configurations.ColdStartConfiguration;
import vkr.dto.ColdStartAnswer;
import vkr.dto.ColdStartEventInfoDto;
import vkr.dto.ColdStartEventsDto;
import vkr.exceptions.EventNotFundException;
import vkr.exceptions.WrongFavoriteIdException;
import vkr.models.ColdStartUserAnswer;
import vkr.models.Event;
import vkr.repositories.ColdStartUserAnswerRepository;
import vkr.repositories.EventRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ColdStartService {
    private final ColdStartConfiguration coldStartConfiguration;

    private final EventRepository eventRepository;
    private final ColdStartUserAnswerRepository coldStartUserAnswerRepository;

    private final CSVWriterService csvWriterService;

    public ColdStartService(ColdStartConfiguration coldStartConfiguration,
                            EventRepository eventRepository,
                            ColdStartUserAnswerRepository coldStartUserAnswerRepository,
                            CSVWriterService csvWriterService) {
        this.coldStartConfiguration = coldStartConfiguration;
        this.eventRepository = eventRepository;
        this.coldStartUserAnswerRepository = coldStartUserAnswerRepository;
        this.csvWriterService = csvWriterService;
    }

    public ColdStartEventsDto getColdStartEvents(){
        LocalDate currentDate = LocalDate.now();
        Integer nElements = coldStartConfiguration.getColdStartEventsCountAtPage() * coldStartConfiguration.getColdStartEventsCountPage();

        List<ColdStartEventInfoDto> events = eventRepository
                .getNRandomElementIds(
                        nElements,
                        currentDate.plusDays(-coldStartConfiguration.getColdStartEventsInterval()),
                        currentDate)
                .stream()
                .map(eventRepository::findById)
                .filter(Optional::isPresent)
                .map(event ->  new ColdStartEventInfoDto(event.get().getId(), event.get().getTitle()))
                .collect(Collectors.toList());

        int nActualPages = events.size() / coldStartConfiguration.getColdStartEventsCountAtPage();

        return new ColdStartEventsDto(
                events.subList(0, nActualPages * coldStartConfiguration.getColdStartEventsCountAtPage()),
                coldStartConfiguration.getColdStartEventsCountAtPage(),
                nActualPages);
    }

    public void setUserAnswersForColdStart(List<ColdStartAnswer> coldStartAnswers, Integer userId) throws WrongFavoriteIdException, EventNotFundException {
        for(ColdStartAnswer answer : coldStartAnswers){
            setUserAnswerForColdStart(answer, userId);
        }
    }

    public void setUserAnswerForColdStart(ColdStartAnswer coldStartAnswers, Integer userId) throws EventNotFundException, WrongFavoriteIdException {
        List<ColdStartUserAnswer> coldStartUserAnswers = new ArrayList<>();
        long nFavoriteEvents = coldStartAnswers
                .getEventIds()
                .stream()
                .filter(eventId -> eventId.equals(coldStartAnswers.getFavoriteId()))
                .count();

        if (nFavoriteEvents != 1 && coldStartAnswers.getFavoriteId() != -1){
            throw new WrongFavoriteIdException();
        }

        for (Long eventId : coldStartAnswers.getEventIds()){
            Optional<Event> optionalEvent = eventRepository.findById(eventId);
            Event event = optionalEvent.orElseThrow(EventNotFundException::new);

            if (coldStartAnswers.getFavoriteId() == -1){
                coldStartUserAnswers.add(new ColdStartUserAnswer(event, userId, false, LocalTime.now()));
            } else {
                coldStartUserAnswers.add(new ColdStartUserAnswer(event, userId, checkFavoriteEvent(event, coldStartAnswers), LocalTime.now()));
            }
        }

        coldStartUserAnswerRepository.saveAll(coldStartUserAnswers);
    }

    public String saveColdStartAnswers(){
        return csvWriterService.saveColdStartUserAnswerToCSV(coldStartUserAnswerRepository.findAll());
    }

    public String saveColdStartEvents(){
        return csvWriterService.saveEventDataToCSV(eventRepository.findAll());
    }

    private boolean checkFavoriteEvent(Event event, ColdStartAnswer coldStartAnswers){
        return event.getId().equals(coldStartAnswers.getFavoriteId());
    }
}
