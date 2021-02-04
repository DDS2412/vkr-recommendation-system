package vkr.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Accessors(chain = true)
public class EventDateDto {
    @JsonProperty("start_date")
    private LocalDate startDate;

    @JsonProperty("start_time")
    private LocalTime startTime;

    @JsonProperty("end_time")
    private LocalTime endTime;

}
