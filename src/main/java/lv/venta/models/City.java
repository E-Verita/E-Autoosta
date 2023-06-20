package lv.venta.models;

import java.util.Collection;
import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name="city_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class City {
	
	@Setter(value = AccessLevel.NONE)
	@Column(name = "Idct")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idct;
	
	@Column(name = "Title")
	@NotNull
	@Pattern(regexp = "[A-ZĒŪĪĻĶŠĀŽČŅ]{1}[a-zēūīļķšāžčņ\\ ]+", message = "Pirmajam burtam jābūt lielajam")
	@Size(min = 3, max = 25)
	private String title;
	
	@Column(name = "Adressofstation")
	@NotNull
	@Pattern(regexp = "^[A-ZĒŪĪĻĶŠĀŽČŅa.-zēūīļķšāžčņ.\\d]+(\\s[A-ZĒŪĪĻĶŠĀŽČŅa-zēūīļķšāžčņ]+)*\\s\\d+[a-zA-Z]?$", message = "Nepareizs adrešu formāts. Adreses paraugs: Lielā iela 5")
	@Size(min = 6, max = 40)
	private String adressofstation;
	
	
	//MTM
	@ToString.Exclude
	@ManyToMany(mappedBy = "cities")
	private Collection<Trip> trips = new ArrayList<>();
	
	

	public City(
			@NotNull @Pattern(regexp = "[A-ZĒŪĪĻĶŠĀŽČŅ]{1}[a-zēūīļķšāžčņ\\ ]+", message = "Pirmajam burtam jābūt lielajam") @Size(min = 3, max = 25) String title,
			@NotNull @Pattern(regexp = "^[A-ZĒŪĪĻĶŠĀŽČŅa.-zēūīļķšāžčņ.\\d]+(\\s[A-ZĒŪĪĻĶŠĀŽČŅa-zēūīļķšāžčņ]+)*\\s\\d+[a-zA-Z]?$", message = "Nepareizs adrešu formāts. Adreses paraugs: Lielā iela 5") @Size(min = 6, max = 40) String adressofstation) {
		this.title = title;
		this.adressofstation = adressofstation;
	}
	
	
	//MTM - add
	public void addTrip(Trip inputTrip) {
		if (!trips.contains(inputTrip)) {
			trips.add(inputTrip);
		}
	}
	
	//MTM - delete
	public void removeTrip(Trip inputTrip) {
		if (trips.contains(inputTrip)) {
			trips.remove(inputTrip);
		}
	}

	
	
	

}
