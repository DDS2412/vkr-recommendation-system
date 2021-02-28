package vkr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import vkr.configurations.ColdStartConfiguration;

@SpringBootApplication
@EnableConfigurationProperties(ColdStartConfiguration.class)
public class SpringApplicationColdStart {
    public static void main(String[] args) { SpringApplication.run(SpringApplicationColdStart.class, args); }
}
