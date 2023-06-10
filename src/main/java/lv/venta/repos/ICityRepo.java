package lv.venta.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.controllers.City;

public interface ICityRepo extends CrudRepository<City, Long > {

}
