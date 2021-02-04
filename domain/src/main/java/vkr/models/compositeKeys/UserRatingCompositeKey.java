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
public class UserRatingCompositeKey implements Serializable {
    @Column(name = "liker_id")
    private Integer likerId;

    @Column(name = "user_id")
    private Integer userId;

    public UserRatingCompositeKey() { }
}
