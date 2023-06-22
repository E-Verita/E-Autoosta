package lv.venta.services.impl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.models.Driver;
import lv.venta.models.Trip;
import lv.venta.repos.ITripRepo;
import lv.venta.services.IDriverCRUDService;
import lv.venta.services.ITripService;

@Service
public class TripServiceImplWithDB implements ITripService {
	
	@Autowired
    private ITripRepo tripRepo;
	
	@Autowired
	private IDriverCRUDService driverService;
	
	@Override
	// selectAllTripsByCityTitle - atgriež visus tos autobusa reisus, kuri ietver pilsētu, kuras nosaukums ir padots funkcijā kā parametrs
	public ArrayList<Trip> selectAllTripsByCityTitle(String cityTitle) throws Exception {
	    ArrayList<Trip> trips = tripRepo.findByCities_Title(cityTitle);
	    if (trips.isEmpty()) {
	        throw new Exception("Nav ceļojumu ar šo pilsētu!");
	    }
	    return trips;
	}
	

	@Override
	public ArrayList<Trip> selectAllTripsByDriverId(long driverId) throws Exception {
	    ArrayList<Trip> trips = tripRepo.findByDriver_Idd(driverId);
	    if (trips.isEmpty()) {
	        throw new Exception("Nav ceļojumu ar šo vadītāja id!");
	    }
	    return trips;
	}

	
	@Override
	public ArrayList<Trip> selectAllTripsToday() throws Exception {
	    LocalDateTime currentDate = LocalDateTime.now();
	    ArrayList<Trip> trips = tripRepo.findByStartdatetimeBetween(currentDate.with(LocalTime.MIN), currentDate.with(LocalTime.MAX));
	    if (trips.isEmpty()) {
	        throw new Exception("Nav ceļojumu šodien!");
	    }
	    return trips;
	}

	@Override
	public void changeTripDriverByDriverId(long driverId, long tripId) throws Exception {
	    Driver driver = driverService.selectDriverById(driverId);
	    Optional<Trip> tripOp = tripRepo.findById(tripId);
	    
	    if (driver != null && tripOp.isPresent()) {
	        Trip trip = tripOp.get();
	        trip.setDriver(driver);
	        tripRepo.save(trip);
	    } else {
	        throw new Exception("Nepareizs Driver ID vai Trip ID");
	    }
	}


	@Override
	public ArrayList<Trip> getAllTrips() throws Exception {
	    try {
	        return (ArrayList<Trip>) tripRepo.findAll();
	    } catch (Exception e) {
	        throw new Exception("Neizdevās iegūt ceļojumu ierakstus no datubāzes: " + e.getMessage());
	    }
	}
	
}
