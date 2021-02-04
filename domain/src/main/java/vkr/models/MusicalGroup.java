package vkr.models;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Data
@Accessors(chain = true)
@Entity
@Table(name="musical_groups")
public class MusicalGroup implements Identifiable<Long> {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "musicalGroups")
    private List<Music> musics;
}
