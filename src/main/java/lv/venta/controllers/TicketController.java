package lv.venta.controllers;

import java.time.LocalDateTime;
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
import lv.venta.models.Cashier;
import lv.venta.models.Driver;
import lv.venta.models.Ticket;
import lv.venta.models.Trip;
import lv.venta.services.ICashierService;
import lv.venta.services.ITicketService;
import lv.venta.services.ITripService;

@Controller
@RequestMapping("/ticket")
public class TicketController {
	
	@Autowired
	private ITicketService ticketService;
	
	@Autowired
	private ITripService tripService;
	
	@Autowired
	ICashierService cashierService;

	
	@GetMapping("/showAll/onlyChild")
	public String getAllChildTickets(Model model) {
		try {
			ArrayList<Ticket> tickets = ticketService.selectAllChildTickets();
			model.addAttribute("tickets", tickets);
			model.addAttribute("searchedElement", "Child Tickets");
			return "ticket-all-page";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/showAll/less/{threshold}")
	public String getAllTicketsBelowPriceThreshold(@PathVariable float threshold, Model model) {
		try {
			ArrayList<Ticket> tickets = ticketService.selectAllTicketsWherePriceIsLowerThan(threshold);
			model.addAttribute("tickets", tickets);
			model.addAttribute("searchedElement", "All Tickets Below Price Threshold " + threshold);
			return "ticket-all-page";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/showAll/trip/{tripid}")
	public String getAllTicketsByTripId(@PathVariable long tripid, Model model) {
		try {
			ArrayList<Ticket> tickets = ticketService.selectAllTicketsByTripId(tripid);
			model.addAttribute("tickets", tickets);
			model.addAttribute("searchedElement", "All Tickets for Trip ID " + tripid);
			return "ticket-all-page";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "error-page";
		}
	}

	
	@GetMapping("/calculate/trip/{tripid}")
	public String calculateIncomeOfTripByTripId(@PathVariable long tripid, Model model) {
		try {
			float income = ticketService.calculateIncomeOfTripByTripId(tripid);
			model.addAttribute("income", income);
			model.addAttribute("searchedElement", "Income for Trip ID " + tripid);
			ArrayList<Ticket> tickets = ticketService.selectAllTicketsByTripId(tripid);
		    model.addAttribute("tickets", tickets);
			return "ticket-income-page";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "error-page";
		}
	}
	
	
	@GetMapping("/calculate/cashier/{cashierid}")
	public String calculateIncomeOfCashierByCashierId(@PathVariable long cashierid, Model model) {
	    try {
	        float income = ticketService.calculateIncomeOfCashierByCashierId(cashierid);
	        model.addAttribute("income", income);
	        model.addAttribute("searchedElement", "Income for Cashier ID " + cashierid);
	        ArrayList<Ticket> tickets = ticketService.selectAllTicketsByCashierId(cashierid);
	        model.addAttribute("tickets", tickets);
	        return "ticket-income-page";
	    } catch (Exception e) {
	        model.addAttribute("error", e.getMessage());
	        return "error-page";
	    }
	}
	
	@GetMapping("/add")
	public String showAddTicketForm(Model model) throws Exception {
	    model.addAttribute("ticket", new Ticket());
	    model.addAttribute("cashiers", cashierService.getAllCashiers());
	    model.addAttribute("trips", tripService.getAllTrips());
	    return "ticket-add-page";
	}

	@PostMapping("/add")
	public String addNewTicket(@Valid @ModelAttribute Ticket ticket, BindingResult result, Model model) throws Exception {
	    if (!result.hasErrors()) {
	        try {
	            ticketService.insertNewTicket(ticket.getPurchasedatetime(), ticket.getTrip(), ticket.getPrice(), ticket.getIsChild(), ticket.getCashier());
	            return "redirect:/ticket/showAll/trip/" + ticket.getTrip().getIdtr();
	        } catch (Exception e) {
	            model.addAttribute("error", e.getMessage());
	            return "error-page";
	        }
	    } else {
	        model.addAttribute("cashiers", cashierService.getAllCashiers());
	        model.addAttribute("trips", tripService.getAllTrips());
	        return "ticket-add-page";
	    }
	}
}
