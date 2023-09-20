package ar.unlam.intraconsulta;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class TestProfeComision {

	@Test
	public void queSePuedaAsignarUnPorfesorAUnaComision() {
		Profesor profe = new Profesor(1, "Jose", "Gomez", 20111000);
		CicloLectivo cicloLectivo = new CicloLectivo(1, LocalDate.of(2023, 8, 14), LocalDate.of(2023, 12, 2), 
				LocalDate.of(2023, 7, 31), LocalDate.of(2023, 8, 10));
		Comision comision = new Comision(1, new Materia(1, 2623, "PB2"), 2326, cicloLectivo, Turnos.MANIANA);
		ProfesorComision profeComision = new ProfesorComision(1, profe, comision);
		
		assertNotNull(profeComision);
		assertEquals("Jose", profeComision.getProfesor().getNombre());
	}
	
	@Test
	public void queSePuedaCompararDosAsignacionesDeProfesAComisionPorId() {
		Profesor profe = new Profesor(1, "Jose", "Gomez", 20111000);
		CicloLectivo cicloLectivo = new CicloLectivo(1, LocalDate.of(2023, 8, 14), LocalDate.of(2023, 12, 2), 
				LocalDate.of(2023, 7, 31), LocalDate.of(2023, 8, 10));
		Comision comision = new Comision(1, new Materia(1, 2623, "PB2"), 2326, cicloLectivo, Turnos.MANIANA);
		ProfesorComision profeComision1 = new ProfesorComision(1, profe, comision);
		ProfesorComision profeComision2 = new ProfesorComision(2, profe, comision);
		
		assertNotEquals(profeComision1, profeComision2);
	}
}
