package vkr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.reactive.function.client.WebClient;
import vkr.configurations.DownloadConfigurationProperties;

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
@EnableConfigurationProperties(DownloadConfigurationProperties.class)
@EnableAsync
public class SpringApplicationDataloader {
    public static void main(String[] args) { SpringApplication.run(SpringApplicationDataloader.class, args); }

    @Bean
    public WebClient webClient() { return WebClient.create(); }
}
