package sp.microservices.rating.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_rating")
public class Rating {
    @Id
    @Column
    private String ratingId;
    @Column
    private String userId;
    @Column
    private String hotelId;
    @Column
    private  int rating;
    @Column
    private  String feedback;

    @Transient
    private Hotel hotel;

}