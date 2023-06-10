package lv.venta.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "trip_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Trip {

	@Setter(value = AccessLevel.NONE)
	@Column(name = "Idtr")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idtr;

	// MTM Cities
	@ManyToMany
	@ToString.Exclude
	@JoinTable(name = "trip_city_table", joinColumns = @JoinColumn(name = "Idtr"), inverseJoinColumns = @JoinColumn(name = "Idct"))
	private Collection<City> cities = new ArrayList<>();

	// MTO ar driver
	@ManyToOne
	@JoinColumn(name = "Idd") // PK Driver
	private Driver driver;

	@NotNull(message = "Ievadiet sākuma datumu un laiku")
	@Column(name = "Startdatetime")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Future(message = "Brauciena sākumam jābūt nākotnē")
	private LocalDateTime startdatetime;

	@Column(name = "Duration")
	@NotNull(message = "Ievadiet brauciena ilgumu")
	@Positive(message = "Brauciena ilgumam ir jabūt pozitīvam skaitlim")
	private Float duration;

	// OTM ar Ticket
	@OneToMany(mappedBy = "trip")
	@ToString.Exclude
	private Collection<Ticket> tickets;

	public Trip(ArrayList<City> cities, Driver driver,
			@NotNull(message = "Ievadiet sākuma datumu un laiku") @Future(message = "Brauciena sākumam jābūt nākotnē") LocalDateTime startdatetime,
			@NotNull(message = "Ievadiet brauciena ilgumu") @Positive(message = "Brauciena ilgumam ir jabūt pozitīvam skaitlim") Float duration) {
		this.cities = cities;
		this.driver = driver;
		this.startdatetime = startdatetime;
		this.duration = duration;
	}

	//MTM - add
	public void addCity(City inputCity) {
		if (!cities.contains(inputCity)) {
			cities.add(inputCity);
		}
	}
	//MTM - delete
	public void removeCity(City inputCity) {
		if (cities.contains(inputCity)) {
			cities.remove(inputCity);
		}
	}

}
