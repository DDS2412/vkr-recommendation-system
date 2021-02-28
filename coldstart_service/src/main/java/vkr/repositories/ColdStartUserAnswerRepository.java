package vkr.repositories;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;
import vkr.models.ColdStartUserAnswer;

@Repository
public interface ColdStartUserAnswerRepository extends JpaRepositoryImplementation<ColdStartUserAnswer, Long> {
}
