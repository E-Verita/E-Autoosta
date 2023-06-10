package lv.venta;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lv.venta.controllers.Buscategory;
import lv.venta.controllers.Cashier;
import lv.venta.controllers.City;
import lv.venta.controllers.Driver;
import lv.venta.controllers.Ticket;
import lv.venta.controllers.Trip;
import lv.venta.repos.ICashierRepo;
import lv.venta.repos.ICityRepo;
import lv.venta.repos.IDriverRepo;
import lv.venta.repos.ITicketRepo;
import lv.venta.repos.ITripRepo;

@SpringBootApplication
public class ProgInzMdApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgInzMdApplication.class, args);
	}
	
	@Bean //izsauksies automātiski, kad startēsies sistēma
	public CommandLineRunner testModel(IDriverRepo driverRepo,ICashierRepo cashierRepo, ITripRepo tripRepo, ITicketRepo ticketRepo, ICityRepo cityRepo) {
		return new CommandLineRunner( ) {

			@Override
			public void run(String... args) throws Exception {
				
				//DRIVER
				Driver dr1 = new Driver("Jānis", "Šoferis", Arrays.asList(Buscategory.SCHOOLBUS, Buscategory.MINIBUS));
				Driver dr2 = new Driver("Ivo", "Braucējs", Arrays.asList(Buscategory.LARGEBUS));
				Driver dr3 = new Driver("Ulvis", "Piestūris", Arrays.asList(Buscategory.LARGEBUS, Buscategory.SCHOOLBUS));
				Driver dr4 = new Driver("Anna", "Ceļvede", Arrays.asList(Buscategory.SCHOOLBUS, Buscategory.MINIBUS, Buscategory.LARGEBUS));
				driverRepo.save(dr1);
				driverRepo.save(dr2);
				driverRepo.save(dr3);
				driverRepo.save(dr4);


				//CASHIER
				Cashier ca1 = new Cashier("Ieva", "Pardevēja");
				Cashier ca2 = new Cashier("Una", "Kasiere");
				cashierRepo.save(ca1);
				cashierRepo.save(ca2);

				//CITY
				City city1 = new City("Rīga", "Lielā iela 1");
				City city2 = new City("Jelgava", "Brīvības iela 10");
				City city3 = new City("Liepāja", "Kuršu iela 5");
				City city4 = new City("Daugavpils", "Centra iela 7");
				City city5 = new City("Ventspils", "Pasta iela 3");
				cityRepo.save(city1);
				cityRepo.save(city2);
				cityRepo.save(city3);
				cityRepo.save(city4);
				cityRepo.save(city5);
				
				//TRIPS
				Trip trip1 = new Trip(new ArrayList<>(Arrays.asList(city1, city2)), dr1, LocalDateTime.now().plusDays(1), 2.5f);
				Trip trip2 = new Trip(new ArrayList<>(Arrays.asList(city3)), dr2, LocalDateTime.now().plusDays(2), 3.0f);
				Trip trip3 = new Trip(new ArrayList<>(Arrays.asList(city1, city4, city5)), dr3, LocalDateTime.now().plusDays(10), 1.5f);
			
				tripRepo.save(trip1);
				tripRepo.save(trip2);
				tripRepo.save(trip3);
					
				trip1.addCity(city1);
				trip1.addCity(city2);
				trip2.addCity(city3);
				trip3.addCity(city1);
				trip3.addCity(city4);
				trip3.addCity(city5);
			
				tripRepo.save(trip1);
				tripRepo.save(trip2);
				tripRepo.save(trip3);

				//TICKETS
				Ticket ticket1 = new Ticket(LocalDateTime.now(), trip1, 10.5f, false, ca1);
				Ticket ticket2 = new Ticket(LocalDateTime.now(), trip2, 8.0f, true, ca2);
				Ticket ticket3 = new Ticket(LocalDateTime.now(), trip3, 12.75f, false, ca1);

				ticketRepo.save(ticket1);
				ticketRepo.save(ticket2);
				ticketRepo.save(ticket3);


			}
			
		};
	}

}
