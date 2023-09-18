package ar.unlam.intraconsulta;

import static org.junit.Assert.*;

import java.time.LocalDate;
import org.junit.Test;

public class TestComision {

	@Test
	public void queSePuedaInstanciarUnaComisionQueSeCursaUnDiaALaSemana() {
		LocalDate fechaInicio = LocalDate.of(2023, 8, 14), fechaFinalizacion = LocalDate.of(2023, 12, 2), 
				inicioInscripcion = LocalDate.of(2023, 7, 31), finalInscripcion = LocalDate.of(2023, 8, 10);
		CicloLectivo cicloLectivo = new CicloLectivo(1, fechaInicio, fechaFinalizacion, inicioInscripcion, finalInscripcion);
		Materia materia = new Materia(2625, "Base de Datos 1");
		DiaCurso dc1 = new DiaCurso(DiasSemana.MARTES, Turnos.MANIANA);
		Comision comision = new Comision(1, materia, 2363, cicloLectivo, dc1);

		assertNotNull(comision);
		assertEquals(1, comision.getDiasCursada().size());
	}
	
	@Test
	public void queSePuedaInstanciarUnaComisionQueSeCursaDosDiasALaSemana() {
		LocalDate fechaInicio = LocalDate.of(2023, 8, 14), fechaFinalizacion = LocalDate.of(2023, 12, 2), 
				inicioInscripcion = LocalDate.of(2023, 7, 31), finalInscripcion = LocalDate.of(2023, 8, 10);
		CicloLectivo cicloLectivo = new CicloLectivo(1, fechaInicio, fechaFinalizacion, inicioInscripcion, finalInscripcion);
		Materia materia = new Materia(2625, "Base de Datos 1");
		DiaCurso dc1 = new DiaCurso(DiasSemana.MARTES, Turnos.MANIANA);
		DiaCurso dc2 = new DiaCurso(DiasSemana.SABADO, Turnos.MANIANA);
		Comision comision = new Comision(1, materia, 2363, cicloLectivo, dc1, dc2);
		
		assertNotNull(comision);
		assertEquals(2, comision.getDiasCursada().size());
	}
	
	@Test
	public void queSePuedaAsignarUnAulaAUnaComision() {
		Integer numeroAula = 140;
		LocalDate fechaInicio = LocalDate.of(2023, 8, 14), fechaFinalizacion = LocalDate.of(2023, 12, 2), 
				inicioInscripcion = LocalDate.of(2023, 7, 31), finalInscripcion = LocalDate.of(2023, 8, 10);
		CicloLectivo cicloLectivo = new CicloLectivo(1, fechaInicio, fechaFinalizacion, inicioInscripcion, finalInscripcion);
		Materia materia = new Materia(2625, "Base de Datos 1");
		DiaCurso dc1 = new DiaCurso(DiasSemana.MARTES, Turnos.MANIANA);
		DiaCurso dc2 = new DiaCurso(DiasSemana.SABADO, Turnos.MANIANA);
		Comision comision = new Comision(1, materia, 2363, cicloLectivo, dc1, dc2);
		
		comision.setAula(new Aula(140, 120));
		
		assertEquals(2, comision.getDiasCursada().size());
		assertNotNull(comision.getAula());
		assertEquals(numeroAula, comision.getAula().getNumeroAula());
	}
	
	@Test
	public void queSePuedaCompararDosComisiones() {
		LocalDate fechaInicio = LocalDate.of(2023, 8, 14), fechaFinalizacion = LocalDate.of(2023, 12, 2), 
				inicioInscripcion = LocalDate.of(2023, 7, 31), finalInscripcion = LocalDate.of(2023, 8, 10);
		CicloLectivo cicloLectivo = new CicloLectivo(1, fechaInicio, fechaFinalizacion, inicioInscripcion, finalInscripcion);
		Materia materia = new Materia(2625, "Base de Datos 1");
		
		DiaCurso dc1 = new DiaCurso(DiasSemana.MARTES, Turnos.MANIANA);
		Comision comision1 = new Comision(1, materia, 2363, cicloLectivo, dc1);
		
		DiaCurso dc2 = new DiaCurso(DiasSemana.MARTES, Turnos.MANIANA);
		Comision comision2 = new Comision(2, materia, 2363, cicloLectivo, dc2);

		assertNotNull(comision1);
		assertNotNull(comision2);
		assertNotEquals(comision1, comision2);
	}
	
}
