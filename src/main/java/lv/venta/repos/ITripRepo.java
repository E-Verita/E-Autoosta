package lv.venta.repos;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.models.Trip;

public interface ITripRepo extends CrudRepository<Trip, Long > {
	ArrayList <Trip> findByCities_Title(String cityTitle);
	
	ArrayList<Trip> findByDriver_Idd(long driverId);

	ArrayList<Trip> findByStartdatetimeBetween(LocalDateTime with, LocalDateTime with2);

}
