package lv.venta.models;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "driver_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Driver extends Person{
	
	@Setter(value=AccessLevel.NONE)
	@Column(name="Idd")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idd;
	
	//MTM ar Buscategory
	@ToString.Exclude
	@NotNull 
	@ElementCollection
	@CollectionTable(name = "driver_category", joinColumns = @JoinColumn(name = "driver_id"))
	@Column(name = "Categories")
	@Enumerated(EnumType.STRING) //EnumType.ORDINAL ja vajag attelot index
	private Collection<Buscategory> categories;
	
	// OTM ar Trip
	@OneToMany(mappedBy="driver")
	@ToString.Exclude
	private Collection <Trip> trips;
	

	public Driver(
			@Pattern(regexp = "[A-ZĀČĒĪĶĻŅŠŪŽ]{1}[a-zāčēīķļņšūž\\ ]+", message = "Pirmajam burtam jābūt lielajam") @NotNull @Size(min = 3, max = 15) String name,
			@NotNull @Size(min = 3, max = 15) @Pattern(regexp = "[A-ZĀČĒĪĶĻŅŠŪŽ]{1}[a-zāčēīķļņšūž\\ ]+", message = "Pirmajam burtam jābūt lielajam") String surname,
			@NotNull Collection<Buscategory> categories) {
		super(name, surname);
		this.categories = categories;
	}



	

}
