package vkr.services;

import com.amazonaws.services.s3.AmazonS3;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import vkr.configurations.ColdStartConfiguration;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class AWSDataService {
    private final ColdStartService coldStartService;

    private final ColdStartConfiguration coldStartConfiguration;

    private  final AmazonS3 s3client;

    public AWSDataService(ColdStartService coldStartService,
                          ColdStartConfiguration coldStartConfiguration,
                          AmazonS3 s3client){
        this.coldStartService = coldStartService;
        this.coldStartConfiguration = coldStartConfiguration;
        this.s3client = s3client;
    }

    // Вызов методов каждый день в 10 утра
    @Scheduled(cron="0 0 10 * * ?")
    public void saveColdStartDataToAmazonS3(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
        String now = LocalDateTime.now().format(formatter);

        String coldStartAnswersFilePath = coldStartService.saveColdStartAnswers();
        s3client.putObject(
                coldStartConfiguration.getBucketName(),
                coldStartConfiguration.getColdStartDataPath() + "/" + now + "_" + coldStartAnswersFilePath,
                new File(coldStartAnswersFilePath)
        );

        String coldStartEventsFilePath = coldStartService.saveColdStartEvents();
        s3client.putObject(
                coldStartConfiguration.getBucketName(),
                coldStartConfiguration.getColdStartDataPath() + "/" + now + "_" + coldStartEventsFilePath,
                new File(coldStartEventsFilePath)
        );
    }
}
