package vkr.repositories;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;
import vkr.models.EventImage;

@Repository
public interface EventImageRepository extends JpaRepositoryImplementation<EventImage, Long> {
}
