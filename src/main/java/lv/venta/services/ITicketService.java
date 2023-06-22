package lv.venta.services;

import java.time.LocalDateTime;
import java.util.ArrayList;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lv.venta.models.Cashier;
import lv.venta.models.Ticket;
import lv.venta.models.Trip;

public interface ITicketService {
	//selectAllChildTickets - atgriež visas bērnu biļetes
    ArrayList<Ticket> selectAllChildTickets() throws Exception;

	//electAllTicketsWherePriceIsLow - atgriež visas biļetes, kuras cena ir mazāka par padoto sliekšņa vērtību (piemēram, 5	eur)
    ArrayList<Ticket> selectAllTicketsWherePriceIsLowerThan(float price) throws Exception;

	//selectAllTicketsByTripId - atgriež visas biļetes uz konkrēto autobusa reisu, funkcijā padodot autobusa reisa id
    ArrayList<Ticket> selectAllTicketsByTripId(long tripId) throws Exception;

	//calculateIncomeOfTripByTripId - aprēķina un atgriež kopējo biļešu summu konkrētajam autobusa reisam, funkcijā padodot autobusa reisa id
    float calculateIncomeOfTripByTripId(long tripId) throws Exception;
    
    //calculateIncomeOfCashierByCashierId - aprēķina un atgriež kopējo summu no biļetēm, ko pārdevis konkrēts kasieris, funkcijā padodot kasiera id
    float calculateIncomeOfCashierByCashierId(long cashierId) throws Exception;

    //insertNewTicketByTripId - pievieno jaunu biļeti uz konkrētu autobusa reisu, funkcijā padodot autobusa reisa id un  informāciju par jauno biļeti
    void insertNewTicketByTripId(long tripId) throws Exception;

	ArrayList<Ticket> selectAllTicketsByCashierId(long cashierid) throws Exception;

	void insertNewTicket(LocalDateTime purchasedatetime, Trip trip, float price, boolean isChild, Cashier cashier);

}
