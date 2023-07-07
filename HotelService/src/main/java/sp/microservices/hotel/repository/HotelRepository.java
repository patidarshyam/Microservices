package sp.microservices.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sp.microservices.hotel.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel,String>
{
    //if you want to implement any custom method or query
    //write
}