package vkr.repositories;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;
import vkr.models.EventCategory;

@Repository
public interface EventCategoryRepository extends JpaRepositoryImplementation<EventCategory, Integer> {
    boolean existsByKudaGoId(Integer kudaGoId);

    EventCategory findFirstBySlug(String slug);
}

