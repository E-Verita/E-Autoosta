package lv.venta.models;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "ticket_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Ticket {

	@Setter(value = AccessLevel.NONE)
	@Column(name = "Idt")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idt;
	
	@Column(name = "Purchasedatetime")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")//
	private LocalDateTime purchasedatetime;
	
	// MTO ar Trip
	@ManyToOne
	@JoinColumn(name = "Idtr") // PK Trip
	private Trip trip;
	
	
	
	@Column(name = "Price")
	@NotNull(message = "Ievadiet cenu")
	@Min(value = 1, message = "Cena nevar būt mazāka par 1")
	@Max(value = 1000, message = "Cena nevar būt lielāka par 1000")
	private Float price;
	
	@Column(name = "Ischild")
	@NotNull(message = "Ievadiet pasažiera tipu")
	private Boolean ischild;

	// MTO ar Cashier
	@ManyToOne
	@JoinColumn(name = "Idc") // PK Cashier
	private Cashier cashier;

	public Ticket(LocalDateTime purchasedatetime, Trip trip,
			@NotNull(message = "Ievadiet cenu") @Min(value = 1, message = "Cena nevar būt mazāka par 1") @Max(value = 1000, message = "Cena nevar būt lielāka par 1000") float price,
			@NotNull(message = "Ievadiet pasažiera tipu") boolean ischild, Cashier cashier) {
		this.purchasedatetime = purchasedatetime;
		this.trip = trip;
		this.price = price;
		this.ischild = ischild;
		this.cashier = cashier;
	}
	
	 @PrePersist
	    public void prePersist() {
		 purchasedatetime = LocalDateTime.now();
	    }
	
	

}
