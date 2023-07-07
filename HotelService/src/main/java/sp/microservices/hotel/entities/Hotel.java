package sp.microservices.hotel.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "hotel")
public class Hotel {

    @Id
    private  String id;
    @Column
    private  String name;
    @Column
    private  String location;
    @Column
    private  String about;

}