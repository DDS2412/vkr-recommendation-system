package vkr.models;

import lombok.Data;
import lombok.experimental.Accessors;
import vkr.models.compositeKeys.EventRatingCompositeKey;

import javax.persistence.*;

@Entity
@Table(name = "event_ratings")
@Data
@Accessors(chain = true)
public class EventRating implements Identifiable<EventRatingCompositeKey> {

    @EmbeddedId
    private EventRatingCompositeKey id;

    @ManyToOne
    @MapsId("event_id")
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "is_liked")
    private Boolean isLiked;

    @Column(name = "is_checked")
    private Boolean isChecked;

    @Column(name = "is_personal_recommended")
    private Boolean isPersonalRecommended;

    @Override
    public EventRatingCompositeKey getId(){
        return this.id;
    }
}
