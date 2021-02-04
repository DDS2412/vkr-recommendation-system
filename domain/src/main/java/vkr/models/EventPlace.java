package vkr.models;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "places",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"kuda_go_id", "address", "title", "short_title"})})
public class EventPlace implements Identifiable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "place_generator")
    @Column(name = "id")
    private Long id;

    @Column(name = "kuda_go_id")
    private Long kudaGoId;

    @Column(name = "title")
    private String title;

    @Column(name = "address")
    private String address;

    @Column(name = "short_title")
    private String shortTitle;

    private String location;

    @Column(name = "site_url")
    private String siteUrl;

    @Column(name = "subway")
    private String subway;

    @Override
    public Long getId(){
        return this.id;
    }
}
