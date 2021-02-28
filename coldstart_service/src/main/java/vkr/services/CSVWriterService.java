package vkr.services;

import org.springframework.stereotype.Service;
import vkr.configurations.ColdStartConfiguration;
import vkr.models.ColdStartUserAnswer;

import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

@Service
public class CSVWriterService {
    private final ColdStartConfiguration coldStartConfiguration;

    public CSVWriterService(ColdStartConfiguration coldStartConfiguration) {
        this.coldStartConfiguration = coldStartConfiguration;
    }

    public void saveColdStartUserAnswerToCSV(List<ColdStartUserAnswer> coldStartUserAnswers){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("user_id, event_id, is_favorite, survey_time\n");

        for (ColdStartUserAnswer coldStartUserAnswer : coldStartUserAnswers){
            stringBuilder = addRowWithColdStartUserAnswer(stringBuilder, coldStartUserAnswer);
        }

        try(PrintWriter writer = new PrintWriter(new File(coldStartConfiguration.getColdStartUserAnswersOutputFile()))) {
            writer.write(stringBuilder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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

    private void createFolderIfDontExist(){
        File directory = new File(coldStartConfiguration.getColdStartDataPath());
        if (!directory.exists()){
            directory.mkdir();
        }
    }
}
