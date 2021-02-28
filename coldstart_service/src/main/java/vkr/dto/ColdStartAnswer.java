package vkr.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ColdStartAnswer {
    private List<Long> eventIds;

    // Если -1, то никакой ивент не понравился пользователю
    private Long favoriteId;
}
