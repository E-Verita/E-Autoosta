package lv.venta.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lv.venta.models.Buscategory;
import lv.venta.models.Driver;
import lv.venta.services.IDriverCRUDService;

@Controller
@RequestMapping("/driver")
public class DriverController {
	
	@Autowired
	private IDriverCRUDService crudService;
	
	
	@GetMapping("/hello") // tiks izsaukta, ja url būs localhost:8080/hello
	public String helloFunc() {
		System.out.println("Mans pirmais kontrolieris ir nostrādājis");
		return "hello-page"; // tiks parādīta hello-page.html lapa
	}
	
	
	//Get - /driver/showAll
	@GetMapping("/showAll")
	public String showAllDrivers(Model model) {
		model.addAttribute("drivers", crudService.selectAllDrivers());
		return "driver-all-page";
	}
	
	
	//Get - /driver/showAll/{id}
	@GetMapping("/showAll/{id}")
	public String showDriverById(@PathVariable long id, Model model) {
		try {
			model.addAttribute("driver", crudService.selectDriverById(id));
			return "driver-one-page";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "error-page";
		}
	}
			
	//Get - /driver/remove/{id}
	@GetMapping("/remove/{id}")
	public String removeDriverById(@PathVariable("id") long id, Model model) {
		try {
			crudService.deleteDriverById(id);
			model.addAttribute("myAllProducts", crudService.selectAllDrivers());
			return "redirect:/driver/showAll";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "error-page";
		}
	}
	
	
	//Get and Post- /driver/addNew
	@GetMapping("/addNew")
	public String showAddDriverForm(Model model) {
		model.addAttribute("driver", new Driver());
		return "driver-add-page";
	}

	@PostMapping("/addNew")
	public String addNewDriver(@Valid @ModelAttribute Driver driver, BindingResult result, Model model) {
	    if (!result.hasErrors()) {
	        try {
	            crudService.insertNewDriver(driver.getName(), driver.getSurname(), driver.getCategories());
	            return "redirect:/driver/showAll";
	        } catch (Exception e) {
	            model.addAttribute("error", e.getMessage());
	            return "error-page";
	        }
	    } else {
	        return "driver-add-page";
	    }
	}

	//Get and Post - /driver/update/{id}
	@GetMapping("/update/{id}")
	public String showUpdateDriverForm(@PathVariable long id, Model model) {
	    try {
	        Driver driver = crudService.selectDriverById(id);
	        model.addAttribute("driver", driver);
	        return "driver-update-page";
	    } catch (Exception e) {
	        model.addAttribute("error", e.getMessage());
	        return "error-page";
	    }
	}

	@PostMapping("/update/{id}")
	public String updateDriverById(@PathVariable long id, @Valid @ModelAttribute Driver driver, BindingResult result, Model model) {
	    if (!result.hasErrors()) {
	        try {
	            crudService.updateDriverById(id, driver.getName(), driver.getSurname(), driver.getCategories());
	            return "redirect:/driver/showAll";
	        } catch (Exception e) {
	            model.addAttribute("error", e.getMessage());
	            return "error-page";
	        }
	    } else {
	        return "driver-update-page";
	    }
	}

}
