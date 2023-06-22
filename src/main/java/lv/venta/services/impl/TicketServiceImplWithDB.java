package lv.venta.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.models.Cashier;
import lv.venta.models.Ticket;
import lv.venta.models.Trip;
import lv.venta.repos.ITicketRepo;
import lv.venta.repos.ITripRepo;
import lv.venta.services.ITicketService;

@Service
public class TicketServiceImplWithDB implements ITicketService {

	@Autowired
	private ITicketRepo ticketRepo;

	@Autowired
	private ITripRepo tripRepo;

	@Override
	public ArrayList<Ticket> selectAllChildTickets() throws Exception {
		ArrayList<Ticket> tickets = ticketRepo.findAllByIsChildTrue();
		if (tickets.isEmpty()) {
			throw new Exception("Nav bērnu biļešu!");
		}
		return tickets;
	}

	@Override
	public ArrayList<Ticket> selectAllTicketsWherePriceIsLowerThan(float price) throws Exception {
		if (price <= 0) {
			throw new Exception("Meklējamā cena nevar būt 0 vai negatīvs skaitlis.");
		}
		return ticketRepo.findAllByPriceLessThan(price);
	}

	@Override
	public ArrayList<Ticket> selectAllTicketsByTripId(long tripId) throws Exception {
		ArrayList<Ticket> tickets = ticketRepo.findAllByTripIdtr(tripId);
		if (tickets.isEmpty()) {
			throw new Exception("Nav biļešu ar id " + tripId);
		}
		return tickets;
	}

	@Override
	public float calculateIncomeOfTripByTripId(long tripId) throws Exception {
		ArrayList<Ticket> tickets = ticketRepo.findAllByTripIdtr(tripId);
		if (tickets.isEmpty()) {
			throw new Exception("Nav biļešu ar id " + tripId);
		}

		float income = 0;
		for (Ticket ticket : tickets) {
			income += ticket.getPrice();
		}
		return income;
	}

	@Override
	public float calculateIncomeOfCashierByCashierId(long cashierId) throws Exception {
		ArrayList<Ticket> tickets = ticketRepo.findAllByCashierIdc(cashierId);
		if (tickets.isEmpty()) {
			throw new Exception("Nav biļešu, kuras būtu pārdevis kasieris/kasiere ar id " + cashierId);
		}

		float income = 0;
		for (Ticket ticket : tickets) {
			income += ticket.getPrice();
		}
		return income;
	}

	@Override
	public void insertNewTicketByTripId(long tripId) throws Exception {
		Optional<Trip> tripOp = tripRepo.findById(tripId);
		if (tripOp.isPresent()) {
			Trip trip = tripOp.get();
			Ticket newTicket = new Ticket();
			newTicket.setTrip(trip);
			ticketRepo.save(newTicket);
		} else {
			throw new Exception("Nepareizs Driver ID vai Trip ID");
		}
	}

	@Override
	public ArrayList<Ticket> selectAllTicketsByCashierId(long cashierid) throws Exception {
		ArrayList<Ticket> tickets = ticketRepo.findAllByCashierIdc(cashierid);
		if (tickets.isEmpty()) {
			throw new Exception("Nav biļešu kasierim ar id " + cashierid);
		}
		return tickets;
	}

	@Override
	public void insertNewTicket(LocalDateTime purchasedatetime, Trip trip, float price, boolean isChild,
			Cashier cashier) {
		ticketRepo.save(new Ticket(purchasedatetime, trip, price, isChild, cashier));
	}

}
