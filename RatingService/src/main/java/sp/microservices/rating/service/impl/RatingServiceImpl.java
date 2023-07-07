package sp.microservices.rating.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sp.microservices.rating.entities.Rating;
import sp.microservices.rating.repository.RatingRepository;
import sp.microservices.rating.exception.ResourceNotFoundException;
import sp.microservices.rating.service.RatingService;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository repository;

    @Autowired
    private RestTemplate restTemplate;

//    @Autowired
//    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(sp.microservices.rating.service.impl.RatingServiceImpl.class);

    @Override
    public Rating saveRating(Rating rating) {
        //generate  unique userid
        String randomUserId = UUID.randomUUID().toString();
        rating.setRatingId(randomUserId);
        return repository.save(rating);
    }

    @Override
    public List<Rating> getAllRating() {
        //implement RATING SERVICE CALL: USING REST TEMPLATE
        return repository.findAll();
    }

    @Override
    public Rating getRating(String ratingId) {
        Rating rating = repository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! : " + ratingId));

        return rating;
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        List<Rating> ratings = repository.findByUserId(userId);

        return ratings;
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return repository.findByHotelId(hotelId);
    }
    //get single user
    /*@Override
    public User getUser(String userId) {
        //get user from database with the help  of user repository
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! : " + userId));
        // fetch rating of the above  user from RATING SERVICE
        //http://localhost:8083/ratings/users/47e38dac-c7d0-4c40-8582-11d15f185fad

        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
        logger.info("{} ", ratingsOfUser);
        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
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

        user.setRatings(ratingList);

        return user;
    }*/

}