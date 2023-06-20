package lv.venta.services;

import java.util.ArrayList;
import java.util.Collection;

import lv.venta.models.Buscategory;
import lv.venta.models.Driver;

public interface IDriverCRUDService {
	//selectAllDriver - atgriež visus šoferus, kas ir saglabāti sistēmā
	ArrayList<Driver> selectAllDrivers();

	//selectDriverrById - atgriež vienu šoferi pēc tā id
	Driver selectDriverById(long id) throws Exception;
	
	//deleteDriverById - dzēš šoferi pēc tā id
	void deleteDriverById(long id) throws Exception;

	//insertNewDriver - pievieno jaunu šoferi sistēmā
	void insertNewDriver(String name, String surname, Collection<Buscategory> collection);
    
	//updateDriverById - rediģē esošo šoferi
	Driver updateDriverById(long id, String name, String surname, Collection<Buscategory> collection) throws Exception;




}
