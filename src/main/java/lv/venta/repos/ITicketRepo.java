package lv.venta.repos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import lv.venta.models.Ticket;

public interface ITicketRepo extends CrudRepository<Ticket, Long > {

	ArrayList<Ticket> findAllByIsChildTrue();

    ArrayList<Ticket> findAllByPriceLessThan(float price);
    
    ArrayList<Ticket> findAllByTripIdtr(long tripId);
    
    ArrayList<Ticket> findAllByCashierIdc(long cashierId);



}
