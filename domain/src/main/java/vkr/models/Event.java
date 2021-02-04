package vkr.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "events",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"kuda_go_id", "title", "short_title", "place_id", "start_date"})},
        indexes = {@Index(name = "index_event_search", columnList = "id,short_title,start_date", unique = true)})
public class Event implements Identifiable<Long> {

    public Event(Event other) {
        this(null, other.getKudaGoId(), other.getVkId(), other.getTitle(), other.getShortTitle(),
                other.getStartDate(), other.getStartTime(), other.getEndTime(), other.getPlace(),
                other.getCategories(), other.getEventRatings(), null, other.getDescription(),
                other.getSiteUrl(), other.isFree(), other.priorityRating == null ? 1 : other.priorityRating);

        this.setImages(other.getImages().stream().map(EventImage::new).collect(Collectors.toList()));
    }


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_generator")
    private Long id;

    @Column(name = "kuda_go_id")
    private Long kudaGoId;

    @Column(name = "vk_id")
    private Long vkId;

    @Column(name = "title")
    private String title;

    @Column(name = "short_title")
    private String shortTitle;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name =  "start_time")
    private LocalTime startTime;

    @Column(name =  "end_time")
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "place_id", referencedColumnName = "id")
    private EventPlace place;

    @ManyToMany
    @JoinTable(
            name = "events_categories",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<EventCategory> categories;

    @OneToMany(mappedBy = "event")
    private List<EventRating> eventRatings;

    @OneToMany(mappedBy = "event", targetEntity = EventImage.class, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventImage> images;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "site_url")
    private String siteUrl;

    @Column(name = "is_free")
    private boolean isFree;

    @Column(name = "priority_rating")
    private Integer priorityRating;

    @Override
    public Long getId(){
        return this.id;
    }
}
