package ar.unlam.intraconsulta;

import static org.junit.Assert.*;

import java.time.LocalDate;
import org.junit.Test;

public class TestUniversidad {

	@Test
	public void queSePuedaInstanciarUnaUniversidad() {
		Universidad universidad = new Universidad("UNLaM");
		
		assertNotNull(universidad);
	}
	
	@Test
	public void queSePuedaAgregarUnCicloLectivo() {
		Universidad universidad = new Universidad("UNLaM");
		
		LocalDate fechaInicio = LocalDate.of(2023, 8, 14), fechaFinalizacion = LocalDate.of(2023, 12, 2), 
				inicioInscripcion = LocalDate.of(2023, 7, 31), finalInscripcion = LocalDate.of(2023, 8, 10);
		CicloLectivo cicloLectivo = new CicloLectivo(universidad.getIdCicloLectivo(), fechaInicio, fechaFinalizacion, inicioInscripcion, finalInscripcion);
		
		assertTrue(universidad.agregarCicloLectivo(cicloLectivo));
		assertEquals(1, universidad.getListaCiclosLectivos().size());
	}
	
	@Test
	public void queSePuedaAgregarDosCiclosLectivos() {
		Universidad universidad = new Universidad("UNLaM");
		
		CicloLectivo cicloLectivo1 = new CicloLectivo(universidad.getIdCicloLectivo(), LocalDate.of(2023, 8, 14), LocalDate.of(2023, 12, 2), LocalDate.of(2023, 7, 31), LocalDate.of(2023, 8, 10));
		CicloLectivo cicloLectivo2 = new CicloLectivo(universidad.getIdCicloLectivo(), LocalDate.of(2023, 4, 3), LocalDate.of(2023, 7, 15), LocalDate.of(2023, 3, 6), LocalDate.of(2023, 3, 13));
		
		assertTrue(universidad.agregarCicloLectivo(cicloLectivo1));
		assertTrue(universidad.agregarCicloLectivo(cicloLectivo2));
		assertEquals(2, universidad.getListaCiclosLectivos().size());
	}
	
	@Test
	public void queNoSePuedaAgregarDosCicloLectivoConMismoId() {
		Universidad universidad = new Universidad("UNLaM");
		
		LocalDate fechaInicio = LocalDate.of(2023, 8, 14), fechaFinalizacion = LocalDate.of(2023, 12, 2), 
				inicioInscripcion = LocalDate.of(2023, 7, 31), finalInscripcion = LocalDate.of(2023, 8, 10);
		CicloLectivo cicloLectivo = new CicloLectivo(universidad.getIdCicloLectivo(), fechaInicio, fechaFinalizacion, inicioInscripcion, finalInscripcion);
		
		assertTrue(universidad.agregarCicloLectivo(cicloLectivo));
		assertFalse(universidad.agregarCicloLectivo(cicloLectivo));
		assertEquals(1, universidad.getListaCiclosLectivos().size());
	}
	
	@Test
	public void queNoSePuedanSuperponerLosIntervalosDeDosCiclosLectivos() {
		Universidad universidad = new Universidad("UNLaM");
		
		CicloLectivo cicloLectivo1 = new CicloLectivo(universidad.getIdCicloLectivo(), LocalDate.of(2023, 8, 14), LocalDate.of(2023, 12, 2), LocalDate.of(2023, 7, 31), LocalDate.of(2023, 8, 10));
		CicloLectivo cicloLectivo2 = new CicloLectivo(universidad.getIdCicloLectivo(), LocalDate.of(2023, 6, 12), LocalDate.of(2023, 10, 7), LocalDate.of(2023, 5, 29), LocalDate.of(2023, 6, 7));
		
		assertTrue(universidad.agregarCicloLectivo(cicloLectivo1));
		assertFalse(universidad.agregarCicloLectivo(cicloLectivo2));
		assertEquals(1, universidad.getListaCiclosLectivos().size());
	}

	@Test
	public void queSePuedaAgregarUnaMateria() {
		Universidad universidad = new Universidad("UNLaM");
		Materia nuevaMateria = new Materia(universidad.getIdMateria(), 2619, "PB1");
		
		assertTrue(universidad.agregarMateria(nuevaMateria));
		assertEquals(1, universidad.getListaMaterias().size());
	}
	
	@Test
	public void queSePuedaAgregarDosMaterias() {
		Universidad universidad = new Universidad("UNLaM");
		Materia nuevaMateria1 = new Materia(universidad.getIdMateria(), 2619, "PB1");
		Materia nuevaMateria2 = new Materia(universidad.getIdMateria(), 2620, "Informatica General");
		
		assertTrue(universidad.agregarMateria(nuevaMateria1));
		assertTrue(universidad.agregarMateria(nuevaMateria2));
		assertEquals(2, universidad.getListaMaterias().size());
	}
	
	@Test
	public void queNoSePuedaAgregarDosMateriasConMismoId() {
		Universidad universidad = new Universidad("UNLaM");
		Materia nuevaMateria = new Materia(universidad.getIdMateria(), 2619, "PB1");
		
		assertTrue(universidad.agregarMateria(nuevaMateria));
		assertFalse(universidad.agregarMateria(nuevaMateria));
		assertEquals(1, universidad.getListaMaterias().size());
	}
	
	/*******************************************************************************************************************************************************/
	
	@Test
	public void queSePuedaAgregarUnaComision() {
		Universidad universidad = new Universidad("UNLaM");
		
		LocalDate fechaInicio = LocalDate.of(2023, 8, 14), fechaFinalizacion = LocalDate.of(2023, 12, 2), 
				inicioInscripcion = LocalDate.of(2023, 7, 31), finalInscripcion = LocalDate.of(2023, 8, 10);
		CicloLectivo cicloLectivo = new CicloLectivo(universidad.getIdCicloLectivo(), fechaInicio, fechaFinalizacion, inicioInscripcion, finalInscripcion);
		
		Materia nuevaMateria = new Materia(universidad.getIdMateria(), 2619, "PB1");
		
		Comision nuevaComision = new Comision(universidad.getIdComision(), nuevaMateria, 2623, cicloLectivo, Turnos.MANIANA);
		
		assertTrue(universidad.agregarCicloLectivo(cicloLectivo));
		assertTrue(universidad.agregarMateria(nuevaMateria));
		assertTrue(universidad.agregarComision(nuevaComision));
		
		assertEquals(1, universidad.getListaMaterias().size());
		assertEquals(1, universidad.getListaCiclosLectivos().size());
		assertEquals(1, universidad.getListaComisiones().size());
	}
	
	@Test
	public void queSePuedaAgregarDosComisionesParaMismaMateriaYTurno() {
		Universidad universidad = new Universidad("UNLaM");
		
		CicloLectivo cicloLectivo1 = new CicloLectivo(universidad.getIdCicloLectivo(), LocalDate.of(2023, 8, 14), LocalDate.of(2023, 12, 2), LocalDate.of(2023, 7, 31), LocalDate.of(2023, 8, 10));
		CicloLectivo cicloLectivo2 = new CicloLectivo(universidad.getIdCicloLectivo(), LocalDate.of(2023, 4, 3), LocalDate.of(2023, 7, 15), LocalDate.of(2023, 3, 6), LocalDate.of(2023, 3, 13));
		
		Materia nuevaMateria = new Materia(universidad.getIdMateria(), 2619, "PB1");
		
		Comision nuevaComision1 = new Comision(universidad.getIdComision(), nuevaMateria, 2623, cicloLectivo1, Turnos.MANIANA);
		Comision nuevaComision2 = new Comision(universidad.getIdComision(), nuevaMateria, 2623, cicloLectivo2, Turnos.MANIANA);
		
		assertTrue(universidad.agregarMateria(nuevaMateria));
		
		assertTrue(universidad.agregarCicloLectivo(cicloLectivo1));
		assertTrue(universidad.agregarCicloLectivo(cicloLectivo2));
		
		assertTrue(universidad.agregarComision(nuevaComision1));
		assertTrue(universidad.agregarComision(nuevaComision2));
		
		assertEquals(1, universidad.getListaMaterias().size());
		assertEquals(2, universidad.getListaCiclosLectivos().size());
		assertEquals(2, universidad.getListaComisiones().size());
	}
	
	@Test
	public void queSePuedaAgregarDosComisionesParaMismaMateriaYCicloLectivo() {
		Universidad universidad = new Universidad("UNLaM");
		
		LocalDate fechaInicio = LocalDate.of(2023, 8, 14), fechaFinalizacion = LocalDate.of(2023, 12, 2), 
				inicioInscripcion = LocalDate.of(2023, 7, 31), finalInscripcion = LocalDate.of(2023, 8, 10);
		CicloLectivo cicloLectivo = new CicloLectivo(1, fechaInicio, fechaFinalizacion, inicioInscripcion, finalInscripcion);
		
		Materia nuevaMateria = new Materia(1, 2619, "PB1");
		
		Comision nuevaComision1 = new Comision(universidad.getIdComision(), nuevaMateria, 2623, cicloLectivo, Turnos.MANIANA);
		Comision nuevaComision2 = new Comision(universidad.getIdComision(), nuevaMateria, 2623, cicloLectivo, Turnos.NOCHE);
		
		assertTrue(universidad.agregarCicloLectivo(cicloLectivo));
		assertTrue(universidad.agregarMateria(nuevaMateria));
		
		assertTrue(universidad.agregarComision(nuevaComision1));
		assertTrue(universidad.agregarComision(nuevaComision2));
		
		assertEquals(1, universidad.getListaMaterias().size());
		assertEquals(1, universidad.getListaCiclosLectivos().size());
		assertEquals(2, universidad.getListaComisiones().size());
	}
	
	@Test
	public void queSePuedaAgregarDosComisionesParaMismoCicloLectivoYTurno() {
		Universidad universidad = new Universidad("UNLaM");
		LocalDate fechaInicio = LocalDate.of(2023, 8, 14), fechaFinalizacion = LocalDate.of(2023, 12, 2), 
				inicioInscripcion = LocalDate.of(2023, 7, 31), finalInscripcion = LocalDate.of(2023, 8, 10);
		CicloLectivo cicloLectivo = new CicloLectivo(universidad.getIdCicloLectivo(), fechaInicio, fechaFinalizacion, inicioInscripcion, finalInscripcion);
		
		Materia nuevaMateria1 = new Materia(universidad.getIdMateria(), 2619, "PB1");
		Materia nuevaMateria2 = new Materia(universidad.getIdMateria(), 2620, "Informatica General");
		
		Comision nuevaComision1 = new Comision(universidad.getIdComision(), nuevaMateria1, 2623, cicloLectivo, Turnos.MANIANA);
		Comision nuevaComision2 = new Comision(universidad.getIdComision(), nuevaMateria2, 2623, cicloLectivo, Turnos.MANIANA);
		
		assertTrue(universidad.agregarCicloLectivo(cicloLectivo));
		
		assertTrue(universidad.agregarMateria(nuevaMateria1));
		assertTrue(universidad.agregarMateria(nuevaMateria2));
		
		assertTrue(universidad.agregarComision(nuevaComision1));
		assertTrue(universidad.agregarComision(nuevaComision2));
		
		assertEquals(2, universidad.getListaMaterias().size());
		assertEquals(1, universidad.getListaCiclosLectivos().size());
		assertEquals(2, universidad.getListaComisiones().size());
	}
	
	@Test
	public void queNoSePuedaAgregarDosComisionesParaMismaMateriaTurnoYCicloLectivo() {
		Universidad universidad = new Universidad("UNLaM");
		
		LocalDate fechaInicio = LocalDate.of(2023, 8, 14), fechaFinalizacion = LocalDate.of(2023, 12, 2), 
				inicioInscripcion = LocalDate.of(2023, 7, 31), finalInscripcion = LocalDate.of(2023, 8, 10);
		CicloLectivo cicloLectivo = new CicloLectivo(universidad.getIdCicloLectivo(), fechaInicio, fechaFinalizacion, inicioInscripcion, finalInscripcion);
		
		Materia nuevaMateria = new Materia(universidad.getIdMateria(), 2619, "PB1");
		
		Comision nuevaComision1 = new Comision(universidad.getIdComision(), nuevaMateria, 2623, cicloLectivo, Turnos.MANIANA);
		Comision nuevaComision2 = new Comision(universidad.getIdComision(), nuevaMateria, 2623, cicloLectivo, Turnos.MANIANA);
		
		assertTrue(universidad.agregarCicloLectivo(cicloLectivo));
		assertTrue(universidad.agregarMateria(nuevaMateria));
		
		assertTrue(universidad.agregarComision(nuevaComision1));
		assertFalse(universidad.agregarComision(nuevaComision2));
		
		assertEquals(1, universidad.getListaMaterias().size());
		assertEquals(1, universidad.getListaCiclosLectivos().size());
		assertEquals(1, universidad.getListaComisiones().size());
	}
	
	/********************************************************************************************************************************************************/
	
	@Test
	public void queSePuedaAgregarUnAlumno() {
		Universidad universidad = new Universidad("UNLaM");
		
		Alumno alumno = new Alumno(universidad.getIdAlumno(), "Ivan", "Landin", 40193158, LocalDate.of(1997, 4, 2), LocalDate.of(2023, 1, 31));
		
		assertTrue(universidad.agregarAlumno(alumno));
		assertEquals(1, universidad.getListaAlumnos().size());
	}
	
	@Test
	public void queSePuedaAgregarDosAlumnos() {
		Universidad universidad = new Universidad("UNLaM");
		
		Alumno alumno1 = new Alumno(universidad.getIdAlumno(), "Gino", "Alvero", 44123456, LocalDate.of(2002, 6, 22), LocalDate.of(2023, 1, 31));
		Alumno alumno2 = new Alumno(universidad.getIdAlumno(), "Juan", "Perez", 40123456, LocalDate.of(1997, 5, 6), LocalDate.of(2023, 1, 31));
		
		assertTrue(universidad.agregarAlumno(alumno1));
		assertTrue(universidad.agregarAlumno(alumno2));
		assertEquals(2, universidad.getListaAlumnos().size());
	}
	
	@Test
	public void queNoSePuedaAgregarDosAlumnosConMismoDni() {
		Universidad universidad = new Universidad("UNLaM");
		
		Alumno alumno1 = new Alumno(universidad.getIdAlumno(), "Gino", "Alvero", 44123456, LocalDate.of(2002, 6, 22), LocalDate.of(2023, 1, 31));
		Alumno alumno2 = new Alumno(universidad.getIdAlumno(), "Juan", "Perez", 44123456, LocalDate.of(1997, 5, 6), LocalDate.of(2023, 1, 31));
		
		assertTrue(universidad.agregarAlumno(alumno1));
		assertFalse(universidad.agregarAlumno(alumno2));
		assertEquals(1, universidad.getListaAlumnos().size());
	}
	
	@Test
	public void queSePuedaAgregarUnProfesor() {
		Universidad universidad = new Universidad("UNLaM");
		
		Profesor profesor = new Profesor(universidad.getIdProfesor(), "Jose", "Maldonado", 20123456);
		
		assertTrue(universidad.agregarProfesor(profesor));
		assertEquals(1, universidad.getListaProfesores().size());
	}
	
	@Test
	public void queSePuedaAgregarDosProfesores() {
		Universidad universidad = new Universidad("UNLaM");
		
		Profesor profesor1 = new Profesor(universidad.getIdProfesor(), "Jose", "Maldonado", 20123456);
		Profesor profesor2 = new Profesor(universidad.getIdProfesor(), "Laura", "Rossi", 28987456);
		
		assertTrue(universidad.agregarProfesor(profesor1));
		assertTrue(universidad.agregarProfesor(profesor2));
		assertEquals(2, universidad.getListaProfesores().size());
	}
	
	@Test
	public void queNoSePuedaAgregarDosProfesoresConMismoDni() {
		Universidad universidad = new Universidad("UNLaM");
		
		Profesor profesor1 = new Profesor(universidad.getIdProfesor(), "Jose", "Maldonado", 20123456);
		Profesor profesor2 = new Profesor(universidad.getIdProfesor(), "Laura", "Rossi", 20123456);
		
		assertTrue(universidad.agregarProfesor(profesor1));
		assertFalse(universidad.agregarProfesor(profesor2));
		assertEquals(1, universidad.getListaProfesores().size());
	}
}
