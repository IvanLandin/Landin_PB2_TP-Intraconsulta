package ar.unlam.intraconsulta;

import static org.junit.Assert.*;

import java.time.LocalDate;
import org.junit.Test;

public class TestInscripcion {

	@Test
	public void queSePuedaInscribirUnAlumnoAUnaComision() {
		//alumno
		Integer legajo = 175498;
		LocalDate fechaNacimiento = LocalDate.of(1997, 4, 2);
		LocalDate fechaIngreso = LocalDate.of(2023, 4, 3);
		Alumno alumno = new Alumno(175498, "Ivan", "Landin", 40193158, fechaNacimiento, fechaIngreso);
		
		//comision
		Integer numComi = 3300;
		LocalDate fechaInicio = LocalDate.of(2023, 8, 14), fechaFinalizacion = LocalDate.of(2023, 12, 2), 
				inicioInscripcion = LocalDate.of(2023, 7, 31), finalInscripcion = LocalDate.of(2023, 8, 10);
		CicloLectivo cicloLectivo = new CicloLectivo(1, fechaInicio, fechaFinalizacion, inicioInscripcion, finalInscripcion);
		Materia materia = new Materia(1, 2623, "PB2");
		Comision comision = new Comision(1, materia, 3300, cicloLectivo, Turnos.MANIANA);
		
		//inscripcion
		Inscripcion inscripcion = new Inscripcion(1, comision, alumno);
		
		assertNotNull(inscripcion);
		assertEquals(legajo, inscripcion.getAlumno().getLegajo());
		assertEquals(numComi, inscripcion.getComision().getNumeroComision());
	}
	
	@Test
	public void queSePuedaCalificarAUnAlumno() {
		//alumno
		LocalDate fechaNacimiento = LocalDate.of(1997, 4, 2);
		LocalDate fechaIngreso = LocalDate.of(2023, 4, 3);
		Alumno alumno = new Alumno(175498, "Ivan", "Landin", 40193158, fechaNacimiento, fechaIngreso);
		
		//comision
		LocalDate fechaInicio = LocalDate.of(2023, 8, 14), fechaFinalizacion = LocalDate.of(2023, 12, 2), 
				inicioInscripcion = LocalDate.of(2023, 7, 31), finalInscripcion = LocalDate.of(2023, 8, 10);
		CicloLectivo cicloLectivo = new CicloLectivo(1, fechaInicio, fechaFinalizacion, inicioInscripcion, finalInscripcion);
		Materia materia = new Materia(1, 2623, "PB2");
		Comision comision = new Comision(1, materia, 3300, cicloLectivo, Turnos.MANIANA);
		
		//inscripcion
		Integer calificacion = 8;
		Inscripcion inscripcion = new Inscripcion(1, comision, alumno);
		
		assertTrue(inscripcion.calificar(calificacion));
		assertEquals(calificacion, inscripcion.getNota().getPrimerParcial());
	}
	
	@Test
	public void queSePuedaAprobarAUnAlumno() {
		//alumno
		LocalDate fechaNacimiento = LocalDate.of(1997, 4, 2);
		LocalDate fechaIngreso = LocalDate.of(2023, 4, 3);
		Alumno alumno = new Alumno(175498, "Ivan", "Landin", 40193158, fechaNacimiento, fechaIngreso);
		
		//comision
		LocalDate fechaInicio = LocalDate.of(2023, 8, 14), fechaFinalizacion = LocalDate.of(2023, 12, 2), 
				inicioInscripcion = LocalDate.of(2023, 7, 31), finalInscripcion = LocalDate.of(2023, 8, 10);
		CicloLectivo cicloLectivo = new CicloLectivo(1, fechaInicio, fechaFinalizacion, inicioInscripcion, finalInscripcion);
		Materia materia = new Materia(1, 2623, "PB2");
		Comision comision = new Comision(1, materia, 3300, cicloLectivo, Turnos.MANIANA);
		
		//inscripcion
		Integer primerParcial = 8;
		Integer segundoParcial = 7;
		Inscripcion inscripcion = new Inscripcion(1, comision, alumno);
		
		assertTrue(inscripcion.calificar(primerParcial));
		assertTrue(inscripcion.calificar(segundoParcial));
		assertEquals(7.5, inscripcion.getNota().calcularPromedio(), 0.01);
		assertTrue(inscripcion.getAprobada());
	}
	
	@Test
	public void queUnAlumnoPuedaRecuperarUnParcial() {
		//alumno
		LocalDate fechaNacimiento = LocalDate.of(1997, 4, 2);
		LocalDate fechaIngreso = LocalDate.of(2023, 4, 3);
		Alumno alumno = new Alumno(175498, "Ivan", "Landin", 40193158, fechaNacimiento, fechaIngreso);
		
		//comision
		LocalDate fechaInicio = LocalDate.of(2023, 8, 14), fechaFinalizacion = LocalDate.of(2023, 12, 2), 
				inicioInscripcion = LocalDate.of(2023, 7, 31), finalInscripcion = LocalDate.of(2023, 8, 10);
		CicloLectivo cicloLectivo = new CicloLectivo(1, fechaInicio, fechaFinalizacion, inicioInscripcion, finalInscripcion);
		Materia materia = new Materia(1, 2623, "PB2");
		Comision comision = new Comision(1, materia, 3300, cicloLectivo, Turnos.MANIANA);
		
		//inscripcion
		Integer primerParcial = 8;
		Integer segundoParcial = 4;
		Integer recuperatorio = 7;
		Inscripcion inscripcion = new Inscripcion(1, comision, alumno);		
		
		assertTrue(inscripcion.calificar(primerParcial));
		assertTrue(inscripcion.calificar(segundoParcial));
		assertFalse(inscripcion.getAprobada());
		assertTrue(inscripcion.calificar(recuperatorio));
		assertEquals(7.5, inscripcion.getNota().calcularPromedio(), 0.01);
		assertTrue(inscripcion.getAprobada());
	}
	
	@Test
	public void queUnAlumnoNoPuedaRecuperarUnParcialMasDeUnaVez() {
		//alumno
		LocalDate fechaNacimiento = LocalDate.of(1997, 4, 2);
		LocalDate fechaIngreso = LocalDate.of(2023, 4, 3);
		Alumno alumno = new Alumno(175498, "Ivan", "Landin", 40193158, fechaNacimiento, fechaIngreso);
		
		//comision
		LocalDate fechaInicio = LocalDate.of(2023, 8, 14), fechaFinalizacion = LocalDate.of(2023, 12, 2), 
				inicioInscripcion = LocalDate.of(2023, 7, 31), finalInscripcion = LocalDate.of(2023, 8, 10);
		CicloLectivo cicloLectivo = new CicloLectivo(1, fechaInicio, fechaFinalizacion, inicioInscripcion, finalInscripcion);
		Materia materia = new Materia(1, 2623, "PB2");
		Comision comision = new Comision(1, materia, 3300, cicloLectivo, Turnos.MANIANA);
		
		//inscripcion
		Integer primerParcial = 8;
		Integer segundoParcial = 4;
		Integer recuperatorio = 4;
		Integer recuperatorioNoValido = 7;
		Inscripcion inscripcion = new Inscripcion(1, comision, alumno);
		
		assertTrue(inscripcion.calificar(primerParcial));
		assertTrue(inscripcion.calificar(segundoParcial));
		assertFalse(inscripcion.getAprobada());
		
		assertTrue(inscripcion.calificar(recuperatorio));
		assertFalse(inscripcion.calificar(recuperatorioNoValido));
		
		assertEquals(6, inscripcion.getNota().calcularPromedio(), 0.01);
		assertFalse(inscripcion.getAprobada());
	}
	
	@Test
	public void queUnAlumnoNoPuedaRecuperarUnParcial() {
		//alumno
		LocalDate fechaNacimiento = LocalDate.of(1997, 4, 2);
		LocalDate fechaIngreso = LocalDate.of(2023, 4, 3);
		Alumno alumno = new Alumno(175498, "Ivan", "Landin", 40193158, fechaNacimiento, fechaIngreso);
		
		//comision
		LocalDate fechaInicio = LocalDate.of(2023, 8, 14), fechaFinalizacion = LocalDate.of(2023, 12, 2), 
				inicioInscripcion = LocalDate.of(2023, 7, 31), finalInscripcion = LocalDate.of(2023, 8, 10);
		CicloLectivo cicloLectivo = new CicloLectivo(1, fechaInicio, fechaFinalizacion, inicioInscripcion, finalInscripcion);
		Materia materia = new Materia(1, 2623, "PB2");
		Comision comision = new Comision(1, materia, 3300, cicloLectivo, Turnos.MANIANA);
		
		//inscripcion
		Integer primerParcial = 5;
		Integer segundoParcial = 4;
		Inscripcion inscripcion = new Inscripcion(1, comision, alumno);
		
		assertTrue(inscripcion.calificar(primerParcial));
		assertTrue(inscripcion.calificar(segundoParcial));
		
		assertFalse(inscripcion.getNota().getRecupera());
		assertFalse(inscripcion.calificar(5));
		
		assertEquals(4.5, inscripcion.getNota().calcularPromedio(), 0.01);
		assertFalse(inscripcion.getAprobada());
	}

	@Test
	public void queNoSePuedaCargarMasDeTresNotas() {
		//alumno
		LocalDate fechaNacimiento = LocalDate.of(1997, 4, 2);
		LocalDate fechaIngreso = LocalDate.of(2023, 4, 3);
		Alumno alumno = new Alumno(175498, "Ivan", "Landin", 40193158, fechaNacimiento, fechaIngreso);
		
		//comision
		LocalDate fechaInicio = LocalDate.of(2023, 8, 14), fechaFinalizacion = LocalDate.of(2023, 12, 2), 
				inicioInscripcion = LocalDate.of(2023, 7, 31), finalInscripcion = LocalDate.of(2023, 8, 10);
		CicloLectivo cicloLectivo = new CicloLectivo(1, fechaInicio, fechaFinalizacion, inicioInscripcion, finalInscripcion);
		Materia materia = new Materia(1, 2623, "PB2");
		Comision comision = new Comision(1, materia, 3300, cicloLectivo, Turnos.MANIANA);
		
		//inscripcion
		Integer primerParcial = 8;
		Integer segundoParcial = 4;
		Integer recuperatorio = 7;
		Integer notaNoValida = 10;
		Inscripcion inscripcion = new Inscripcion(1, comision, alumno);
		
		assertTrue(inscripcion.calificar(primerParcial));
		assertTrue(inscripcion.calificar(segundoParcial));
		assertTrue(inscripcion.calificar(recuperatorio));
		assertFalse(inscripcion.calificar(notaNoValida));
		assertEquals(7.5, inscripcion.getNota().calcularPromedio(), 0.01);
		assertTrue(inscripcion.getAprobada());
	}
	
	@Test
	public void queNoSePuedaPonerNotaMayorADiezYMenorAUno() {
		//alumno
		LocalDate fechaNacimiento = LocalDate.of(1997, 4, 2);
		LocalDate fechaIngreso = LocalDate.of(2023, 4, 3);
		Alumno alumno = new Alumno(175498, "Ivan", "Landin", 40193158, fechaNacimiento, fechaIngreso);
		
		//comision
		LocalDate fechaInicio = LocalDate.of(2023, 8, 14), fechaFinalizacion = LocalDate.of(2023, 12, 2), 
				inicioInscripcion = LocalDate.of(2023, 7, 31), finalInscripcion = LocalDate.of(2023, 8, 10);
		CicloLectivo cicloLectivo = new CicloLectivo(1, fechaInicio, fechaFinalizacion, inicioInscripcion, finalInscripcion);
		Materia materia = new Materia(1, 2623, "PB2");
		Comision comision = new Comision(1, materia, 3300, cicloLectivo, Turnos.MANIANA);
		
		//inscripcion
		Integer primerParcial = 8;
		Integer segundoParcial = 4;
		Integer recuperatorio = 0;
		Integer notaNoValida = 11;
		Inscripcion inscripcion = new Inscripcion(1, comision, alumno);
		
		assertTrue(inscripcion.calificar(primerParcial));
		assertTrue(inscripcion.calificar(segundoParcial));
		assertFalse(inscripcion.calificar(recuperatorio));
		assertFalse(inscripcion.calificar(notaNoValida));
		
		assertEquals(6, inscripcion.getNota().calcularPromedio(), 0.01);
		assertFalse(inscripcion.getAprobada());
	}
}
