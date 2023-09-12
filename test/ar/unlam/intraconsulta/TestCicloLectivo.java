package ar.unlam.intraconsulta;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

public class TestCicloLectivo {

	@Test
	public void queSePuedaInstanciarUnCicloLectivo() {
		Date fechaInicio = new GregorianCalendar(2023, Calendar.AUGUST, 14).getTime();
		CicloLectivo cicloLectivo = new CicloLectivo(2023, Cuatrimestres.SEGUNDO, fechaInicio);
		
		assertNotNull(cicloLectivo);
	}
	
	@Test
	public void queSePuedanCompararDosCiclosLectivosPorAnioYCuatrimestre() {
		Date fechaInicio1 = new GregorianCalendar(2023, Calendar.AUGUST, 14).getTime();
		Date fechaInicio2 = new GregorianCalendar(2023, Calendar.APRIL, 3).getTime();
		
		CicloLectivo cicloLectivo1 = new CicloLectivo(2023, Cuatrimestres.PRIMER, fechaInicio1);
		CicloLectivo cicloLectivo2 = new CicloLectivo(2023, Cuatrimestres.SEGUNDO, fechaInicio2);
		
		assertNotEquals(cicloLectivo1, cicloLectivo2);
	}

}
