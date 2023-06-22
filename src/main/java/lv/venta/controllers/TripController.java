package lv.venta.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lv.venta.models.Driver;
import lv.venta.models.Trip;
import lv.venta.services.IDriverCRUDService;
import lv.venta.services.ITripService;

@Controller
@RequestMapping("/trip")
public class TripController {
	
	@Autowired
	private ITripService tripService;
	
	@Autowired
	private IDriverCRUDService driverService;
	
	@GetMapping("/showAll/city/{citytitle}")
	public String showAllTripsByCityTitle(@PathVariable String citytitle, Model model) {
		try {
			ArrayList<Trip> trips = tripService.selectAllTripsByCityTitle(citytitle);
			model.addAttribute("trips", trips);
			model.addAttribute("searchedElement", citytitle);
			return "trip-all-page";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "error-page";
		}
	}
	
	
	@GetMapping("/showAll/driver/{driverid}")
	public String showAllTripsByDriverId(@PathVariable long driverid, Model model) {
		try {
			ArrayList<Trip> trips = tripService.selectAllTripsByDriverId(driverid);
			Driver driver = driverService.selectDriverById(driverid);
			model.addAttribute("trips", trips);
			model.addAttribute("searchedElement",  driver.getIdd() + " " + driver.getName() + " " + driver.getSurname());
			return "trip-all-page";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "error-page";
		}
	}
	
	
	@GetMapping("/showAll/today")
	public String showAllTripsToday(Model model) {
		try {
			ArrayList<Trip> trips = tripService.selectAllTripsToday();
			model.addAttribute("trips", trips);
			model.addAttribute("searchedElement", "Today");
			return "trip-all-page";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "error-page";
		}
	}
	
	
	@GetMapping("/changeDriver/{tripid}/{driverid}")
	public String changeTripDriverByDriverId(@PathVariable long tripid, @PathVariable long driverid, Model model) {
		try {
			tripService.changeTripDriverByDriverId(driverid, tripid);
			return "redirect:/trip/showAll";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/showAll")
	public String showAllTrips(Model model) {
	    try {
	        ArrayList<Trip> trips = tripService.getAllTrips();
	        model.addAttribute("trips", trips);
			model.addAttribute("searchedElement", "All Trips");
	        return "trip-all-page";
	    } catch (Exception e) {
	        model.addAttribute("error", e.getMessage());
	        return "error-page";
	    }
	}
	
	
	

}
