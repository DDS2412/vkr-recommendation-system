package vkr.models;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "musics")
public class Music implements Identifiable<Long> {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "musical_group_musics",
            joinColumns = @JoinColumn(name = "music_id"),
            inverseJoinColumns = @JoinColumn(name = "musical_group_id"))
    private List<MusicalGroup> musicalGroups;

    @ManyToMany(mappedBy="musics")
    private List<User> listeners;
}
