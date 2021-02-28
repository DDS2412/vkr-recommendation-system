package vkr.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("vkr")
public class ColdStartConfiguration {
    private String[] coldStartQuestions;

    private Integer coldStartEventsInterval;

    private Integer coldStartEventsCountAtPage;

    private Integer coldStartEventsCountPage;

    private String coldStartUserAnswersOutputFile;

    private String coldStartDataPath;
}
