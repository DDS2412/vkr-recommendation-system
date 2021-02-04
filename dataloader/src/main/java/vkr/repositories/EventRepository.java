package vkr.repositories;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;
import vkr.models.Event;

import java.time.LocalDate;

@Repository
public interface EventRepository extends JpaRepositoryImplementation<Event, Long> {
    boolean existsByKudaGoIdAndStartDate(Long kudaGoId, LocalDate startDate);
}
