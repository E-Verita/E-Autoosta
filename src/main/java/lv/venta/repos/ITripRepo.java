package lv.venta.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.controllers.Trip;

public interface ITripRepo extends CrudRepository<Trip, Long > {
	

}
