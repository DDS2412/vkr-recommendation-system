package vkr.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "cold_start_user_answer")
@AllArgsConstructor
@NoArgsConstructor
public class ColdStartUserAnswer implements Identifiable<Long>  {

    public ColdStartUserAnswer(Event event, Integer userId, Boolean isFavorite, LocalTime surveyTime){
        this.event = event;
        this.userId = userId;
        this.isFavorite = isFavorite;
        this.surveyTime = surveyTime;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_generator")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;

    @Column(name =  "user_id")
    private Integer userId;

    @Column(name =  "is_favorite")
    private Boolean isFavorite;

    @Column(name =  "survey_time")
    private LocalTime surveyTime;
}
