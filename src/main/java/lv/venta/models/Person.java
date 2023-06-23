package lv.venta.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Data
//@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {

	@Column(name = "Name")
	@Pattern(regexp = "[A-ZĀČĒĪĶĻŅŠŪŽ]{1}[a-zāčēīķļņšūž\\ ]+", message = "Pirmajam burtam jābūt lielajam")
	@NotNull(message = "Ievadiet vārdu")
	@Size(min = 3, max = 15, message = "Tekstam jābūt 3 - 15 rakstzīmes garam")
	private String name;

	@NotNull(message = "Ievadiet uzvārdu")
	@Size(min = 3, max = 15, message = "Tekstam jābūt 3 - 15 rakstzīmes garam")
	@Pattern(regexp = "[A-ZĀČĒĪĶĻŅŠŪŽ]{1}[a-zāčēīķļņšūž\\ ]+", message = "Pirmajam burtam jābūt lielajam")
	@Column(name = "Surname")
	private String surname;

	@Override
	public String toString() {
		return "Person [name=" + name + ", surname=" + surname + "]";
	}
	
	

}
