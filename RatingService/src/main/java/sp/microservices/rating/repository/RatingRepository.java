package sp.microservices.rating.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sp.microservices.rating.entities.Rating;

import java.util.List;


public interface RatingRepository extends JpaRepository<Rating,String> {
    //if you want to implement any custom method or query
    List<Rating> findByUserId(String userId);
    List<Rating> findByHotelId(String hotelId);
}