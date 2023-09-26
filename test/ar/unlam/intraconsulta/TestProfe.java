package ar.unlam.intraconsulta;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestProfe {

	@Test
	public void queSePuedaInstanciarUnProfesor() {
		Profesor profe = new Profesor(1, "Jose", "Gomez", 20111000);
		
		assertNotNull(profe);
	}

	@Test
	public void queSePuedaCompararDosProfesoresPorDni() {
		Profesor profe1 = new Profesor(1, "Jose", "Gomez", 20111000);
		Profesor profe2 = new Profesor(2, "Jose", "Gomez", 20111000);
		
		assertEquals(profe1, profe2);
	}
}
