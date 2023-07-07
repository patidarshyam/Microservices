package sp.microservices.rating.service;

import sp.microservices.rating.entities.Rating;

import java.util.List;

public interface RatingService {

    //user operations

    //create
    Rating saveRating(Rating user);

    //get all user
    List<Rating> getAllRating();

    //get single user of given userId

    Rating getRating(String userId);

    /**
     * Get rating by userId
     * @param userId
     * @return
     */
    List<Rating> getRatingByUserId(String userId);

    List<Rating> getRatingByHotelId(String hotelId);

    //TODO: delete
    //TODO: update


}