package lv.venta.services;

import java.util.ArrayList;

import lv.venta.controllers.Ticket;

public interface ITicketService {
	//selectAllChildTickets - atgriež visas bērnu biļetes
    ArrayList<Ticket> selectAllChildTickets();

	//electAllTicketsWherePriceIsLow - atgriež visas biļetes, kuras cena ir mazāka par padoto sliekšņa vērtību (piemēram, 5	eur)
    ArrayList<Ticket> selectAllTicketsWherePriceIsLowerThan(float price);

	//selectAllTicketsByTripId - atgriež visas biļetes uz konkrēto autobusa reisu, funkcijā padodot autobusa reisa id
    ArrayList<Ticket> selectAllTicketsByTripId(long tripId);

	//calculateIncomeOfTripByTripId - aprēķina un atgriež kopējo biļešu summu konkrētajam autobusa reisam, funkcijā padodot autobusa reisa id
    float calculateIncomeOfTripByTripId(long tripId);
    
    //calculateIncomeOfCashierByCashierId - aprēķina un atgriež kopējo summu no biļetēm, ko pārdevis konkrēts kasieris, funkcijā padodot kasiera id
    float calculateIncomeOfCashierByCashierId(long cashierId);

    //insertNewTicketByTripId - pievieno jaunu biļeti uz konkrētu autobusa reisu, funkcijā padodot autobusa reisa id un  informāciju par jauno biļeti
    void insertNewTicketByTripId(long tripId, Ticket ticket);

}
