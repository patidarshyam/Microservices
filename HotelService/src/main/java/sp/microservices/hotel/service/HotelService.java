package sp.microservices.hotel.service;

import sp.microservices.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {

    //hotel operations

    //create
    Hotel saveHotel(Hotel hotel);

    //get all hotel
    List<Hotel> getAllHotel();

    //get single hotel of given hotelId

    Hotel getHotel(String hotelId);

    //TODO: delete
    //TODO: update


}