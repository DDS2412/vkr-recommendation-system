package vkr.models;

import lombok.Data;
import lombok.experimental.Accessors;
import vkr.models.compositeKeys.UserRatingCompositeKey;

import javax.persistence.*;

@Entity
@Table(name = "user_ratings")
@Data
@Accessors(chain = true)
public class UserRating implements Identifiable<UserRatingCompositeKey> {

    @EmbeddedId
    private UserRatingCompositeKey id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("liker_id")
    @JoinColumn(name = "liker_id")
    private User liker;

    @Column(name = "is_liked")
    private Boolean isLiked;

    @Column(name = "is_checked")
    private Boolean isChecked;

    @Column(name = "is_personal_recommended")
    private Boolean isPersonalRecommended;

    @Override
    public UserRatingCompositeKey getId(){
        return this.id;
    }
}
