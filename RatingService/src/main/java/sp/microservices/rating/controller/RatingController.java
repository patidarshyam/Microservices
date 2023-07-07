package sp.microservices.rating.controller;

/*
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sp.microservices.rating.entities.Rating;
import sp.microservices.rating.service.RatingService;
import sp.microservices.rating.entities.Rating;
import sp.microservices.rating.service.RatingService;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    private Logger logger = LoggerFactory.getLogger(RatingController.class);

    @GetMapping("/health")
    public String createRating(){
        return "Rating service running";
    }

    //create
    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        Rating rating1 = ratingService.saveRating(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(rating1);
    }

    //single rating get


    @GetMapping("/{ratingId}")
//    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//    @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
   // @RateLimiter(name = "ratingRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<Rating> getSingleRating(@PathVariable String ratingId) {
        logger.info("Get Single Rating Handler: RatingController");
//        logger.info("Retry count: {}", retryCount);

        Rating rating = ratingService.getRating(ratingId);
        return ResponseEntity.ok(rating);
    }

    //creating fall back  method for circuitbreaker


    public ResponseEntity<Rating> ratingHotelFallback(String ratingId, Exception ex) {
//        logger.info("Fallback is executed because service is down : ", ex.getMessage());

        ex.printStackTrace();

        Rating rating = new Rating();
        //  Rating.builder().email("dummy@gmail.com").name("Dummy").about("This rating is created dummy because some service is down").ratingId("141234").build();
        return new ResponseEntity<>(rating, HttpStatus.BAD_REQUEST);
    }


    //all rating get
    @GetMapping
    public ResponseEntity<List<Rating>> getAllRating() {
        List<Rating> allRating = ratingService.getAllRating();
        return ResponseEntity.ok(allRating);
    }

    //get all of user
    //@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }

    //get all of hotels
    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId) {
        return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }
}