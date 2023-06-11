package lv.venta.services.impl;

import java.util.ArrayList;

import lv.venta.controllers.Buscategory;
import lv.venta.controllers.Driver;

public interface IDriverCRUDService {
	//selectAllDriver - atgriež visus šoferus, kas ir saglabāti sistēmā
	ArrayList<Driver> selectAllDriver();

	//selectDriverrById - atgriež vienu šoferi pēc tā id
	Driver selectDriverrById(long id) throws Exception;

	
	//deleteDriverById - dzēš šoferi pēc tā id
	void deleteDriverById(long id) throws Exception;

	//insertNewDriver - pievieno jaunu šoferi sistēmā
	Driver insertNewDriver(String name, String surname, ArrayList<Buscategory> categories);

	//updateDriverById - rediģē esošo šoferi
	Driver updateDriverById(long id, String name, String surname, ArrayList<Buscategory> categories) throws Exception;


}
