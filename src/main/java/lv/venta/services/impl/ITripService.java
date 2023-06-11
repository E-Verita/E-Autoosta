package lv.venta.services.impl;

import java.util.ArrayList;

import lv.venta.controllers.Trip;

public interface ITripService {
	//selectAlTripsByCityTitle - atgriež visus tos autobusa reisus, kuri ietver pilsētu, kuras nosaukums ir padots funkcijā kā 	parametrs
    ArrayList<Trip> selectAllTripsByCityTitle(String cityTitle);

	//selectAlTripsByDriverId - atgriež visus tos autobusa reisus, kurus vada konkrēts šoferis, funkcijā padodot šofera id
    ArrayList<Trip> selectAllTripsByDriverId(long driverId);

	//selectAlTripsToday - atgriež visus tos autobusa reisus, kuri ir šodien
    ArrayList<Trip> selectAllTripsToday();

	//changeTripDriverByDriverId - nomaina esošā autobusa reisam šoferi, funkcijā padodot gan šofera id, gan reisa id
    void changeTripDriverByDriverId(long driverId, long tripId);

}
