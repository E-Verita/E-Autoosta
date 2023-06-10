package lv.venta.controllers;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@Table(name = "cashier_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Cashier extends Person{
	@Setter(value=AccessLevel.NONE)
	@Column(name="Idc")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idc;
	
	//OTM ar Ticket
	@OneToMany(mappedBy="cashier")
	@ToString.Exclude
	private Collection <Ticket> tickets;
	
	public Cashier(
			@Pattern(regexp = "[A-ZĀČĒĪĶĻŅŠŪŽ]{1}[a-zāčēīķļņšūž\\ ]+", message = "Pirmajam burtam jābūt lielajam") @NotNull @Size(min = 3, max = 15) String name,
			@NotNull @Size(min = 3, max = 15) @Pattern(regexp = "[A-ZĀČĒĪĶĻŅŠŪŽ]{1}[a-zāčēīķļņšūž\\ ]+", message = "Pirmajam burtam jābūt lielajam") String surname) {
		super(name, surname);
	}

}