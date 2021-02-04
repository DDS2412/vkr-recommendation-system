package vkr.models;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "users")
public class User implements Identifiable<Integer> {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "sex")
    private String sex;

    @Column(name =  "birthday")
    private LocalDate birthday;

    @Column(name = "photo_400_orig")
    private String photo400Orig;

    @Column(name = "can_see_audio")
    private Boolean canSeeAudio;

    @OneToMany(mappedBy = "user")
    private List<EventRating> eventRatings;

    @OneToMany(mappedBy = "user")
    private List<UserRating> userRatings;

    @OneToMany(mappedBy = "liker")
    private List<UserRating> likerRatings;

    @ManyToMany
    @JoinTable(
            name = "music_listeners",
            joinColumns = @JoinColumn(name = "listener_id"),
            inverseJoinColumns = @JoinColumn(name = "music_id"))
    private List<Music> musics;

    @Override
    public Integer getId(){
        return this.id;
    }

    public User setBirthday(String birthdayString) {
        if(birthdayString != null){
            List<String> changedBStrings = Arrays.stream(birthdayString.split("\\.")).map(s -> s.length() == 1 ? String.format("0%s", s) : s).collect(Collectors.toList());
            if(changedBStrings.size() == 3){
                Collections.reverse(changedBStrings);
                this.birthday = LocalDate.parse(String.join("-", changedBStrings));

                return this;
            }
        }

        this.birthday = null;
        return this;
    }
}
