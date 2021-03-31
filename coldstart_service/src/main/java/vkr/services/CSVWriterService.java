package vkr.services;

import org.springframework.stereotype.Service;
import vkr.configurations.ColdStartConfiguration;
import vkr.models.ColdStartUserAnswer;
import vkr.models.Event;
import vkr.models.EventCategory;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CSVWriterService {
    private final ColdStartConfiguration coldStartConfiguration;

    public CSVWriterService(ColdStartConfiguration coldStartConfiguration) {
        this.coldStartConfiguration = coldStartConfiguration;
    }

    public String saveColdStartUserAnswerToCSV(List<ColdStartUserAnswer> coldStartUserAnswers){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("user_id, event_id, is_favorite, survey_time\n");

        for (ColdStartUserAnswer coldStartUserAnswer : coldStartUserAnswers){
            stringBuilder = addRowWithColdStartUserAnswer(stringBuilder, coldStartUserAnswer);
        }
        File file = new File(coldStartConfiguration.getColdStartUserAnswersOutputFile());

        try(PrintWriter writer = new PrintWriter(file, "cp1251")) {
            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file.getPath();
    }

    public String saveEventDataToCSV(List<Event> events){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id, short_title, start_date, description, is_free, place_id, place_short_title, category_ids, category_names, category_slags\n");

        for(Event event : events){
            stringBuilder = addRowWithEvent(stringBuilder, event);
        }

        File file = new File(coldStartConfiguration.getColdStartEventOutputFile());

        try(PrintWriter writer = new PrintWriter(file, "cp1251")) {
            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file.getPath();
    }

    private StringBuilder addRowWithColdStartUserAnswer(StringBuilder stringBuilder, ColdStartUserAnswer coldStartUserAnswer){
        stringBuilder.append(coldStartUserAnswer.getUserId());
        stringBuilder.append(',');
        stringBuilder.append(coldStartUserAnswer.getEvent().getId());
        stringBuilder.append(',');
        stringBuilder.append(coldStartUserAnswer.getIsFavorite());
        stringBuilder.append(',');
        stringBuilder.append(coldStartUserAnswer.getSurveyTime());
        stringBuilder.append('\n');

        return stringBuilder;
    }

    private StringBuilder addRowWithEvent(StringBuilder stringBuilder, Event event){
        stringBuilder.append(event.getId());
        stringBuilder.append(',');
        stringBuilder.append(event.getShortTitle());
        stringBuilder.append(',');
        stringBuilder.append(event.getStartDate());
        stringBuilder.append(',');
        stringBuilder.append(event.getDescription());
        stringBuilder.append(',');
        stringBuilder.append(event.isFree());
        stringBuilder.append(',');
        if (event.getPlace() != null) {
            stringBuilder.append(event.getPlace().getId());
            stringBuilder.append(',');
            stringBuilder.append(event.getPlace().getShortTitle());
            stringBuilder.append(',');
        } else {
            stringBuilder.append(',');
            stringBuilder.append(',');
        }

        stringBuilder.append(event.getCategories().stream().map(eventCategory -> eventCategory.getId().toString()).collect(Collectors.joining(":")));
        stringBuilder.append(',');
        stringBuilder.append(event.getCategories().stream().map(EventCategory::getName).collect(Collectors.joining(":")));
        stringBuilder.append(',');
        stringBuilder.append(event.getCategories().stream().map(EventCategory::getSlug).collect(Collectors.joining(":")));

        return stringBuilder;
    }
}
