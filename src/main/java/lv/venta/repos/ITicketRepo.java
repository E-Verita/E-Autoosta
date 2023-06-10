package lv.venta.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.controllers.Driver;
import lv.venta.controllers.Ticket;

public interface ITicketRepo extends CrudRepository<Ticket, Long > {

}
