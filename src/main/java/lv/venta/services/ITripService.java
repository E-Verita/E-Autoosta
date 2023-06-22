package lv.venta.services;

import java.util.ArrayList;

import lv.venta.models.Trip;

public interface ITripService {
	//selectAlTripsByCityTitle - atgriež visus tos autobusa reisus, kuri ietver pilsētu, kuras nosaukums ir padots funkcijā kā 	parametrs
    ArrayList<Trip> selectAllTripsByCityTitle(String cityTitle) throws Exception;

	//selectAlTripsByDriverId - atgriež visus tos autobusa reisus, kurus vada konkrēts šoferis, funkcijā padodot šofera id
    ArrayList<Trip> selectAllTripsByDriverId(long driverId) throws Exception;

	//selectAlTripsToday - atgriež visus tos autobusa reisus, kuri ir šodien
    ArrayList<Trip> selectAllTripsToday() throws Exception;

	//changeTripDriverByDriverId - nomaina esošā autobusa reisam šoferi, funkcijā padodot gan šofera id, gan reisa id
    void changeTripDriverByDriverId(long driverId, long tripId) throws Exception;

	ArrayList<Trip> getAllTrips() throws Exception;
    
    

}
