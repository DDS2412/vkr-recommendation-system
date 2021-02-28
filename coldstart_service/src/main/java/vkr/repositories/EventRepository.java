package vkr.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vkr.models.Event;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepositoryImplementation<Event, Long> {
    boolean existsByKudaGoId(Long kudaGoId);

    @Query("select case when count(e) = :size then true else false end from Event e where e.id in :ids")
    boolean existsEventsById(@Param("ids") List<Long> ids, @Param("size") Long size);


    @Query(value = "select * from events e " +
            "where lower(short_title) like ( lower(:query) || '%') and current_date <= e.start_date " +
            "group by id, start_date order by lower(short_title) using ~<~",
    countQuery = "select count(*) from events e " +
            "where lower(short_title) like ( lower(:query) || '%') and current_date <= e.start_date " +
            "group by id, start_date order by lower(short_title) using ~<~",
            nativeQuery = true)
    Page<Event> searchByNameWithPagination(@Param("query") String query, Pageable pageable);

    @Query(value =
            "select max(e.id) as id from events e where e.start_date >= :start_date and e.start_date <= :end_date group by e.title order by random() limit :n_elements",
            nativeQuery = true)
    List<Long> getNRandomElementIds(@Param("n_elements") Integer nElements,
                                                   @Param("start_date") LocalDate startDate,
                                                   @Param("end_date") LocalDate endDate);
}
