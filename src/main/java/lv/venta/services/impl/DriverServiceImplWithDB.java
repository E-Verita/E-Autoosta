package lv.venta.services.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.models.Buscategory;
import lv.venta.models.Driver;
import lv.venta.repos.IDriverRepo;
import lv.venta.services.IDriverCRUDService;

@Service
public class DriverServiceImplWithDB implements IDriverCRUDService {
	
	@Autowired
	private IDriverRepo driverRepo;
	

	@Override
	public ArrayList<Driver> selectAllDrivers() {
		return (ArrayList<Driver>) driverRepo.findAll();
	}

	@Override
	public Driver selectDriverById(long id) throws Exception {
		if (driverRepo.existsById(id)) {
			return driverRepo.findById(id).get();
		} else {
			throw new Exception("Nepareizs Id");
		}
	}

	@Override
	public void deleteDriverById(long id) throws Exception {
		if (driverRepo.existsById(id)) {
			driverRepo.deleteById(id);
		} else {
			throw new Exception("Nepareizs Id");
		}
	}


		
	@Override
	public void insertNewDriver(String name, String surname, Collection<Buscategory> categories) {
		driverRepo.save(new Driver(name, surname, categories));
	}

	@Override
	public Driver updateDriverById(long id, String name, String surname, Collection<Buscategory> categories)
			throws Exception {
		if (driverRepo.existsById(id)) {
			Driver updatedDr = driverRepo.findById(id).get(); 
			updatedDr.setName(name);
			updatedDr.setSurname(surname);
			updatedDr.setCategories(categories);
			return driverRepo.save(updatedDr);
		} else {
			throw new Exception("Nepareizs Id");
		}
	}




	
}
