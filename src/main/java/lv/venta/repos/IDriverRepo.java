package lv.venta.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.controllers.Driver;

public interface IDriverRepo extends CrudRepository<Driver, Long > {
	
}
