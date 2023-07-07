package sp.microservices.hotel.controller;

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
import sp.microservices.hotel.entities.Hotel;
import sp.microservices.hotel.service.HotelService;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(HotelController.class);

    @GetMapping("/health")
    public String createHotel(){
        return "Running";
    }

    //create
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        Hotel hotel1 = hotelService.saveHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
    }

    //single hotel get


    @GetMapping("/{hotelId}")
//    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//    @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
   // @RateLimiter(name = "hotelRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<Hotel> getSingleHotel(@PathVariable String hotelId) {
        logger.info("Get Single Hotel Handler: HotelController");
//        logger.info("Retry count: {}", retryCount);

        Hotel hotel = hotelService.getHotel(hotelId);
        return ResponseEntity.ok(hotel);
    }

    //creating fall back  method for circuitbreaker


    public ResponseEntity<Hotel> ratingHotelFallback(String hotelId, Exception ex) {
//        logger.info("Fallback is executed because service is down : ", ex.getMessage());

        ex.printStackTrace();

        Hotel hotel = null;
                //Hotel.builder().email("dummy@gmail.com").name("Dummy").about("This hotel is created dummy because some service is down").hotelId("141234").build();
        return new ResponseEntity<>(hotel, HttpStatus.BAD_REQUEST);
    }


    //all hotel get
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotel() {
        List<Hotel> allHotel = hotelService.getAllHotel();
        return ResponseEntity.ok(allHotel);
    }
}