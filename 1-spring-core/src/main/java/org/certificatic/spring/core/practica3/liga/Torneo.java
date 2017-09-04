package org.certificatic.spring.core.practica3.liga;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
@AllArgsConstructor
public class Torneo {
	
	private @Setter(AccessLevel.PROTECTED) String nombre;
	private @Setter(AccessLevel.PROTECTED) Evento evento;

}
