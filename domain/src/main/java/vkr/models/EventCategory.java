package vkr.models;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "event_categories", uniqueConstraints = {@UniqueConstraint(columnNames = {"kuda_go_id", "name", "slug"})})
public class EventCategory implements Identifiable<Integer> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_categories_generator")
    private Integer id;

    @Column(name = "kuda_go_id")
    private Integer kudaGoId;

    @Column(name = "name")
    private String name;

    @Column(name = "slug")
    private String slug;

    @ManyToMany(mappedBy = "categories")
    private List<Event> events;

    @Override
    public Integer getId(){
        return this.id;
    }
}
