package vkr.models.compositeKeys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@Embeddable
public class EventRatingCompositeKey implements Serializable {
    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "user_id")
    private Integer userId;

    public EventRatingCompositeKey() { }
}
