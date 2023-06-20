package lv.venta.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.models.Trip;
import lv.venta.repos.ITripRepo;
import lv.venta.services.ITripService;

@Service
public class TripServiceImplWithDB implements ITripService {
	
	@Autowired
    private ITripRepo tripRepo;

	@Override
	public ArrayList<Trip> selectAllTripsByCityTitle(String cityTitle) {
		return null;
	}

	@Override
	public ArrayList<Trip> selectAllTripsByDriverId(long driverId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Trip> selectAllTripsToday() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeTripDriverByDriverId(long driverId, long tripId) {
		// TODO Auto-generated method stub
		
	}

}
