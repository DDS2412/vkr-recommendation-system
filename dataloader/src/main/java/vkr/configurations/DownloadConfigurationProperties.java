package vkr.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("vkr")
public class DownloadConfigurationProperties {
    private String imageFilePath;

    private float compressionRatio;
}
