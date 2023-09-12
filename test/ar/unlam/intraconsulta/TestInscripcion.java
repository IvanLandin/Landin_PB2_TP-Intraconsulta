package ar.unlam.intraconsulta;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

public class TestInscripcion {

	@Test
	public void queSePuedaInscribirUnAlumnoAUnaComision() {
		Integer legajo = 175498;
		Integer numComi = 3300;
		Date fechaInicio = new GregorianCalendar(2023, Calendar.AUGUST, 14).getTime();
		CicloLectivo cl = new CicloLectivo(2023, Cuatrimestres.SEGUNDO, fechaInicio);
		Materia materia = new Materia(2623, "PB2");
		Comision comision = new Comision(materia, 3300);
		Alumno alumno = new Alumno(175498, "Ivan", "Landin", 40193158);
		Inscripcion inscripcion = new Inscripcion(comision, alumno);
		
		cl.agregarComision(comision);
		
		assertNotNull(inscripcion);
		assertEquals(legajo, inscripcion.getAlumno().getLegajo());
		assertEquals(numComi, inscripcion.getComision().getNumeroComision());
	}
	
	@Test
	public void queSePuedaCalificarAUnAlumno() {
		Integer calificacion = 8;
		Date fechaInicio = new GregorianCalendar(2023, Calendar.AUGUST, 14).getTime();
		CicloLectivo cl = new CicloLectivo(2023, Cuatrimestres.SEGUNDO, fechaInicio);
		Materia materia = new Materia(2623, "PB2");
		Comision comision = new Comision(materia, 3300);
		Alumno alumno = new Alumno(175498, "Ivan", "Landin", 40193158);
		Inscripcion inscripcion = new Inscripcion(comision, alumno);
		
		cl.agregarComision(comision);
		
		assertTrue(inscripcion.calificar(calificacion));
		assertEquals(calificacion, inscripcion.getNota().getPrimerParcial());
	}
	
	@Test
	public void queSePuedaAprobarAUnAlumno() {
		Integer primerParcial = 8;
		Integer segundoParcial = 7;
		Date fechaInicio = new GregorianCalendar(2023, Calendar.AUGUST, 14).getTime();
		CicloLectivo cl = new CicloLectivo(2023, Cuatrimestres.SEGUNDO, fechaInicio);
		Materia materia = new Materia(2623, "PB2");
		Comision comision = new Comision(materia, 3300);
		Alumno alumno = new Alumno(175498, "Ivan", "Landin", 40193158);
		Inscripcion inscripcion = new Inscripcion(comision, alumno);
		
		cl.agregarComision(comision);
		
		assertTrue(inscripcion.calificar(primerParcial));
		assertTrue(inscripcion.calificar(segundoParcial));
		assertEquals(7.5, inscripcion.getNota().calcularPromedio(), 0.01);
		assertTrue(inscripcion.getAprobada());
	}
	
	@Test
	public void queUnAlumnoPuedaRecuperarUnParcial() {
		Integer primerParcial = 8;
		Integer segundoParcial = 4;
		Integer recuperatorio = 7;
		Date fechaInicio = new GregorianCalendar(2023, Calendar.AUGUST, 14).getTime();
		CicloLectivo cl = new CicloLectivo(2023, Cuatrimestres.SEGUNDO, fechaInicio);
		Materia materia = new Materia(2623, "PB2");
		Comision comision = new Comision(materia, 3300);
		Alumno alumno = new Alumno(175498, "Ivan", "Landin", 40193158);
		Inscripcion inscripcion = new Inscripcion(comision, alumno);
		
		cl.agregarComision(comision);
		
		assertTrue(inscripcion.calificar(primerParcial));
		assertTrue(inscripcion.calificar(segundoParcial));
		assertFalse(inscripcion.getAprobada());
		
		assertTrue(inscripcion.calificar(recuperatorio));
		assertEquals(7.5, inscripcion.getNota().calcularPromedio(), 0.01);
		assertTrue(inscripcion.getAprobada());
	}
	
	@Test
	public void queUnAlumnoNoPuedaRecuperarUnParcialMasDeUnaVez() {
		Integer primerParcial = 8;
		Integer segundoParcial = 4;
		Integer recuperatorio = 4;
		Integer recuperatorioNoValido = 7;
		Date fechaInicio = new GregorianCalendar(2023, Calendar.AUGUST, 14).getTime();
		CicloLectivo cl = new CicloLectivo(2023, Cuatrimestres.SEGUNDO, fechaInicio);
		Materia materia = new Materia(2623, "PB2");
		Comision comision = new Comision(materia, 3300);
		Alumno alumno = new Alumno(175498, "Ivan", "Landin", 40193158);
		Inscripcion inscripcion = new Inscripcion(comision, alumno);
		
		cl.agregarComision(comision);
		
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
		Integer primerParcial = 5;
		Integer segundoParcial = 4;
		Date fechaInicio = new GregorianCalendar(2023, Calendar.AUGUST, 14).getTime();
		CicloLectivo cl = new CicloLectivo(2023, Cuatrimestres.SEGUNDO, fechaInicio);
		Materia materia = new Materia(2623, "PB2");
		Comision comision = new Comision(materia, 3300);
		Alumno alumno = new Alumno(175498, "Ivan", "Landin", 40193158);
		Inscripcion inscripcion = new Inscripcion(comision, alumno);
		
		cl.agregarComision(comision);
		
		assertTrue(inscripcion.calificar(primerParcial));
		assertTrue(inscripcion.calificar(segundoParcial));
		
		assertFalse(inscripcion.getNota().getRecupera());
		assertFalse(inscripcion.calificar(5));
		
		assertEquals(4.5, inscripcion.getNota().calcularPromedio(), 0.01);
		assertFalse(inscripcion.getAprobada());
	}

	@Test
	public void queNoSePuedaCargarMasDeTresNotas() {
		Integer primerParcial = 8;
		Integer segundoParcial = 4;
		Integer recuperatorio = 7;
		Integer notaNoValida = 10;
		Date fechaInicio = new GregorianCalendar(2023, Calendar.AUGUST, 14).getTime();
		CicloLectivo cl = new CicloLectivo(2023, Cuatrimestres.SEGUNDO, fechaInicio);
		Materia materia = new Materia(2623, "PB2");
		Comision comision = new Comision(materia, 3300);
		Alumno alumno = new Alumno(175498, "Ivan", "Landin", 40193158);
		Inscripcion inscripcion = new Inscripcion(comision, alumno);
		
		cl.agregarComision(comision);
		
		assertTrue(inscripcion.calificar(primerParcial));
		assertTrue(inscripcion.calificar(segundoParcial));
		assertTrue(inscripcion.calificar(recuperatorio));
		assertFalse(inscripcion.calificar(notaNoValida));
		
		assertEquals(7.5, inscripcion.getNota().calcularPromedio(), 0.01);
		assertTrue(inscripcion.getAprobada());
	}
	
	@Test
	public void queNoSePuedaPonerNotaMayorADiezYMenorAUno() {
		Integer primerParcial = 8;
		Integer segundoParcial = 4;
		Integer recuperatorio = 0;
		Integer notaNoValida = 11;
		Date fechaInicio = new GregorianCalendar(2023, Calendar.AUGUST, 14).getTime();
		CicloLectivo cl = new CicloLectivo(2023, Cuatrimestres.SEGUNDO, fechaInicio);
		Materia materia = new Materia(2623, "PB2");
		Comision comision = new Comision(materia, 3300);
		Alumno alumno = new Alumno(175498, "Ivan", "Landin", 40193158);
		Inscripcion inscripcion = new Inscripcion(comision, alumno);
		
		cl.agregarComision(comision);
		
		assertTrue(inscripcion.calificar(primerParcial));
		assertTrue(inscripcion.calificar(segundoParcial));
		assertFalse(inscripcion.calificar(recuperatorio));
		assertFalse(inscripcion.calificar(notaNoValida));
		
		assertEquals(6, inscripcion.getNota().calcularPromedio(), 0.01);
		assertFalse(inscripcion.getAprobada());
	}
}
