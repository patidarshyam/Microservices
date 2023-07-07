package sp.microservices.hotel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
import sp.microservices.hotel.entities.Hotel;
import sp.microservices.hotel.exception.ResourceNotFoundException;
import sp.microservices.hotel.repository.HotelRepository;
import sp.microservices.hotel.service.HotelService;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

//    @Autowired
//    private RestTemplate restTemplate;

//    @Autowired
//    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class);

    @Override
    public Hotel saveHotel(Hotel hotel) {
        //generate  unique hotelid
        String randomHotelId = UUID.randomUUID().toString();
        hotel.setId(randomHotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotel() {
        //implement RATING SERVICE CALL: USING REST TEMPLATE
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(String hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel with given id is not found on server !! : " + hotelId));

        return hotel;
    }

    //get single hotel
    /*@Override
    public Hotel getHotel(String hotelId) {
        //get hotel from database with the help  of hotel repository
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel with given id is not found on server !! : " + hotelId));
        // fetch rating of the above  hotel from RATING SERVICE
        //http://localhost:8083/ratings/hotels/47e38dac-c7d0-4c40-8582-11d15f185fad

        Rating[] ratingsOfHotel = restTemplate.getForObject("http://RATING-SERVICE/ratings/hotels/" + hotel.getHotelId(), Rating[].class);
        logger.info("{} ", ratingsOfHotel);
        List<Rating> ratings = Arrays.stream(ratingsOfHotel).toList();
        List<Rating> ratingList = ratings.stream().map(rating -> {
            //api call to hotel service to get the hotel
            //http://localhost:8082/hotels/1cbaf36d-0b28-4173-b5ea-f1cb0bc0a791
            //ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            // logger.info("response status code: {} ",forEntity.getStatusCode());
            //set the hotel to rating
            rating.setHotel(hotel);
            //return the rating
            return rating;
        }).collect(Collectors.toList());

        hotel.setRatings(ratingList);

        return hotel;
    }*/

}