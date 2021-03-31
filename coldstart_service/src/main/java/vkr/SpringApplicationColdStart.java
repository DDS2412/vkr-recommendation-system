package vkr;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import vkr.configurations.ColdStartConfiguration;

import java.util.Optional;

@SpringBootApplication
@EnableConfigurationProperties(ColdStartConfiguration.class)
public class SpringApplicationColdStart {
    public static void main(String[] args) { SpringApplication.run(SpringApplicationColdStart.class, args); }

    @Bean
    public AmazonS3 getS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(
                Optional.of(System.getenv("ACCESS_KEY")).orElse(""),
                Optional.of(System.getenv("SECRET_KEY")).orElse("")
        );

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.EU_CENTRAL_1)
                .build();
    }
}
