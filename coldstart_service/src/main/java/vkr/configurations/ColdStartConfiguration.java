package vkr.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("vkr")
public class ColdStartConfiguration {
    private Integer coldStartEventsInterval;

    private Integer coldStartEventsCountAtPage;

    private Integer coldStartEventsCountPage;

    private String coldStartUserAnswersOutputFile;

    private String coldStartEventOutputFile;

    private String coldStartDataPath;

    private String bucketName;
}
