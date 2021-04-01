package vkr.configurations;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import vkr.enums.CategoryEnum;

@Configuration
public class EventConfigurationProperties {
    @Getter
    private final int pageSize = 100;
    @Getter
    private final String eventLocation = "spb";
    @Getter
    private final String categories = String.format("%s,%s,%s,%s",
            CategoryEnum.EXHIBITION.getValue(), CategoryEnum.CONCERT.getValue(),
            CategoryEnum.FESTIVAL.getValue(), CategoryEnum.TOUR.getValue());
    @Getter
    private final String eventFields = "id,title,tagline,short_title,place,description,categories,images,dates,site_url";
    @Getter
    private final String eventPlaceFields = "id,title,short_title,address,location,site_url,subway";

    @Getter
    private final String eventExpand = "dates";

    @Getter
    private final String eventTextFormat = "plain";

    @Getter
    private final Boolean isDownloadImage = false;
}
