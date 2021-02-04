package vkr.repositories;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;
import vkr.models.EventPlace;

@Repository
public interface EventPlaceRepository extends JpaRepositoryImplementation<EventPlace, Long> {
    boolean existsByKudaGoId(Long kudaGoId);

    EventPlace findFirstByKudaGoId(Long kudaGoId);
}
