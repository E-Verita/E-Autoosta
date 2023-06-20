package lv.venta.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.models.Driver;
import lv.venta.models.Ticket;

public interface ITicketRepo extends CrudRepository<Ticket, Long > {

}
