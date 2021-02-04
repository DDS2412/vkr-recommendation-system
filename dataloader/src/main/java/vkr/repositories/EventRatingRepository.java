package vkr.repositories;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;
import vkr.models.EventRating;
import vkr.models.compositeKeys.EventRatingCompositeKey;

@Repository
public interface EventRatingRepository extends JpaRepositoryImplementation<EventRating, EventRatingCompositeKey> {
}
