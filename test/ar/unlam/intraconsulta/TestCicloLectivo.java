package ar.unlam.intraconsulta;

import static org.junit.Assert.*;

import java.time.LocalDate;
import org.junit.Test;

public class TestCicloLectivo {

	@Test
	public void queSePuedaInstanciarUnCicloLectivo() {
		LocalDate fechaInicio = LocalDate.of(2023, 8, 14);
		LocalDate fechaFinalizacion = LocalDate.of(2023, 12, 2);
		LocalDate inicioInscripcion = LocalDate.of(2023, 7, 31);
		LocalDate finalInscripcion = LocalDate.of(2023, 8, 10);
		CicloLectivo cicloLectivo = new CicloLectivo(1, fechaInicio, fechaFinalizacion, inicioInscripcion, finalInscripcion);
		
		assertNotNull(cicloLectivo);
	}
	
	@Test
	public void queSePuedanCompararDosCiclosLectivosPorID() {
		LocalDate fechaInicio1 = LocalDate.of(2023, 8, 14);
		LocalDate fechaFinalizacion1 = LocalDate.of(2023, 12, 2);
		LocalDate inicioInscripcion1 = LocalDate.of(2023, 7, 31);
		LocalDate finalInscripcion1 = LocalDate.of(2023, 8, 10);
		CicloLectivo cicloLectivo1 = new CicloLectivo(1, fechaInicio1, fechaFinalizacion1, inicioInscripcion1, finalInscripcion1);
		
		LocalDate fechaInicio2 = LocalDate.of(2023, 8, 14);
		LocalDate fechaFinalizacion2 = LocalDate.of(2023, 12, 2);
		LocalDate inicioInscripcion2 = LocalDate.of(2023, 7, 31);
		LocalDate finalInscripcion2 = LocalDate.of(2023, 8, 10);
		CicloLectivo cicloLectivo2 = new CicloLectivo(2, fechaInicio2, fechaFinalizacion2, inicioInscripcion2, finalInscripcion2);
		
		assertNotEquals(cicloLectivo1, cicloLectivo2);
	}

}
