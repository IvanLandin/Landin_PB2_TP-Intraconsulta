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
		Materia materia = new Materia(1, 2623, "Programacion Basica 2");
		Comision comision = new Comision(1, materia, 2363, cicloLectivo, Turnos.MANIANA);
		assertTrue(comision.asignarDiaDeCurso(DiasSemana.MIERCOLES));

		assertNotNull(comision);
		assertEquals(1, comision.getListaDiasDeCurso().size());
	}
	
	@Test
	public void queSePuedaInstanciarUnaComisionQueSeCursaDosDiasALaSemana() {
		LocalDate fechaInicio = LocalDate.of(2023, 8, 14), fechaFinalizacion = LocalDate.of(2023, 12, 2), 
				inicioInscripcion = LocalDate.of(2023, 7, 31), finalInscripcion = LocalDate.of(2023, 8, 10);
		CicloLectivo cicloLectivo = new CicloLectivo(1, fechaInicio, fechaFinalizacion, inicioInscripcion, finalInscripcion);
		Materia materia = new Materia(1, 2625, "Base de Datos 1");
		Comision comision = new Comision(1, materia, 2363, cicloLectivo, Turnos.MANIANA);
		assertTrue(comision.asignarDiaDeCurso(DiasSemana.MARTES));
		assertTrue(comision.asignarDiaDeCurso(DiasSemana.SABADO));
		
		assertNotNull(comision);
		assertEquals(2, comision.getListaDiasDeCurso().size());
	}
	
	@Test
	public void queSePuedaAsignarUnAulaAUnaComision() {
		Integer numeroAula = 140;
		LocalDate fechaInicio = LocalDate.of(2023, 8, 14), fechaFinalizacion = LocalDate.of(2023, 12, 2), 
				inicioInscripcion = LocalDate.of(2023, 7, 31), finalInscripcion = LocalDate.of(2023, 8, 10);
		CicloLectivo cicloLectivo = new CicloLectivo(1, fechaInicio, fechaFinalizacion, inicioInscripcion, finalInscripcion);
		Materia materia = new Materia(1, 2625, "Base de Datos 1");
		Comision comision = new Comision(1, materia, 2363, cicloLectivo, Turnos.MANIANA);
		Aula aula = new Aula(140, 120);
		
		assertTrue(comision.asignarDiaDeCurso(DiasSemana.MARTES));
		assertTrue(comision.asignarDiaDeCurso(DiasSemana.SABADO));
		
		assertTrue(comision.asignarAula(aula));
		
		assertEquals(2, comision.getListaDiasDeCurso().size());
		assertNotNull(comision.getAula());
		assertEquals(numeroAula, comision.getAula().getNumeroAula());
	}
	
	@Test
	public void queSePuedaCompararDosComisiones() {
		LocalDate fechaInicio = LocalDate.of(2023, 8, 14), fechaFinalizacion = LocalDate.of(2023, 12, 2), 
				inicioInscripcion = LocalDate.of(2023, 7, 31), finalInscripcion = LocalDate.of(2023, 8, 10);
		CicloLectivo cicloLectivo = new CicloLectivo(1, fechaInicio, fechaFinalizacion, inicioInscripcion, finalInscripcion);
		Materia materia = new Materia(1, 2625, "Base de Datos 1");
		
		Comision comision1 = new Comision(1, materia, 2363, cicloLectivo, Turnos.MANIANA);
		Comision comision2 = new Comision(2, materia, 2363, cicloLectivo, Turnos.MANIANA);
		
		assertNotNull(comision1);
		assertNotNull(comision2);
		assertNotEquals(comision1, comision2);
	}
	
}
