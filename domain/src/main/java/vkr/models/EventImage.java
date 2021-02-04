package vkr.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "event_images")
public class EventImage implements Identifiable<Long> {
    public EventImage(EventImage other){
        this(null, other.getCudagoImage(), other.getImage(), other.getEvent());
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_image_generator")
    private Long id;

    @Column(name = "cudago_image")
    private String cudagoImage;

    private String image;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;

    @Override
    public Long getId(){
        return this.id;
    }
}
