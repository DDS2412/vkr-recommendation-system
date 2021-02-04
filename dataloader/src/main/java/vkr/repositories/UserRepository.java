package vkr.repositories;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;
import vkr.models.User;

@Repository
public interface UserRepository extends JpaRepositoryImplementation<User, Integer> {
}
