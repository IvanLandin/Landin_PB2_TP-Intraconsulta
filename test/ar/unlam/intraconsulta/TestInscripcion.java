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
		Integer calificacion1 = 8;
		Integer calificacion2 = 6;
		Inscripcion inscripcion = new Inscripcion(1, comision, alumno);
		
		assertTrue(inscripcion.calificar(calificacion1, TipoDeNota.PRIMER_PARCIAL));
		assertTrue(inscripcion.calificar(calificacion2, TipoDeNota.SEGUNDO_PARCIAL));
		assertEquals(calificacion1, inscripcion.getListaDeNotas().get(0).getValor());
		assertEquals(calificacion2, inscripcion.getListaDeNotas().get(1).getValor());
	}
	
	@Test
	public void queNoSePuedaRegistrarElMismoTipoDeNotaDosVeces() {
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
		Integer calificacion1 = 8;
		Integer calificacion2 = 6;
		Inscripcion inscripcion = new Inscripcion(1, comision, alumno);
		
		assertTrue(inscripcion.calificar(calificacion1, TipoDeNota.PRIMER_PARCIAL));
		assertFalse(inscripcion.calificar(calificacion2, TipoDeNota.PRIMER_PARCIAL));
		assertEquals(1, inscripcion.getListaDeNotas().size());
		assertEquals(calificacion1, inscripcion.getListaDeNotas().get(0).getValor());
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
		
		assertTrue(inscripcion.calificar(primerParcial, TipoDeNota.PRIMER_PARCIAL));
		assertTrue(inscripcion.calificar(segundoParcial, TipoDeNota.SEGUNDO_PARCIAL));
		assertEquals(7.5, inscripcion.obtenerNotaFinal(), 0.01);
		assertTrue(inscripcion.estaAprobada());
		assertTrue(inscripcion.estaPromocionada());
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
		
		assertTrue(inscripcion.calificar(primerParcial, TipoDeNota.PRIMER_PARCIAL));
		assertTrue(inscripcion.calificar(segundoParcial, TipoDeNota.SEGUNDO_PARCIAL));
		assertTrue(inscripcion.estaAprobada());
		assertTrue(inscripcion.calificar(recuperatorio, TipoDeNota.RECUPERATORIO_SEGUNDO_PARCIAL));
		assertEquals(7.5, inscripcion.obtenerNotaFinal(), 0.01);
		assertTrue(inscripcion.estaAprobada());
		assertTrue(inscripcion.estaPromocionada());
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
		Integer segundoParcial = 2;
		Integer recuperatorio = 4;
		Integer recuperatorioNoValido = 7;
		Inscripcion inscripcion = new Inscripcion(1, comision, alumno);
		
		assertTrue(inscripcion.calificar(primerParcial, TipoDeNota.PRIMER_PARCIAL));
		assertTrue(inscripcion.calificar(segundoParcial, TipoDeNota.SEGUNDO_PARCIAL));
		assertNull(inscripcion.obtenerNotaFinal());
		assertFalse(inscripcion.estaAprobada());
		
		assertTrue(inscripcion.calificar(recuperatorio, TipoDeNota.RECUPERATORIO_SEGUNDO_PARCIAL));
		assertFalse(inscripcion.calificar(recuperatorioNoValido, TipoDeNota.RECUPERATORIO_SEGUNDO_PARCIAL));
		assertEquals(6, inscripcion.obtenerNotaFinal(), 0.01);
		
		assertTrue(inscripcion.estaAprobada());
		assertFalse(inscripcion.estaPromocionada());
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
		
		assertTrue(inscripcion.calificar(primerParcial, TipoDeNota.PRIMER_PARCIAL));
		assertTrue(inscripcion.calificar(segundoParcial, TipoDeNota.SEGUNDO_PARCIAL));
		assertTrue(inscripcion.calificar(recuperatorio, TipoDeNota.RECUPERATORIO_SEGUNDO_PARCIAL));
		assertFalse(inscripcion.calificar(notaNoValida, TipoDeNota.RECUPERATORIO_SEGUNDO_PARCIAL));
		assertEquals(7.5, inscripcion.obtenerNotaFinal(), 0.01);
		assertTrue(inscripcion.estaAprobada());
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
		Integer recuperatorio = 6;
		Integer notaNoValida = 11;
		Inscripcion inscripcion = new Inscripcion(1, comision, alumno);
		
		assertTrue(inscripcion.calificar(primerParcial, TipoDeNota.PRIMER_PARCIAL));
		assertTrue(inscripcion.calificar(segundoParcial, TipoDeNota.SEGUNDO_PARCIAL));
		assertFalse(inscripcion.calificar(notaNoValida, TipoDeNota.RECUPERATORIO_SEGUNDO_PARCIAL));
		assertTrue(inscripcion.calificar(recuperatorio, TipoDeNota.RECUPERATORIO_SEGUNDO_PARCIAL));
		
		assertEquals(7, inscripcion.obtenerNotaFinal(), 0.01);
		assertTrue(inscripcion.estaAprobada());
	}
	
	
}
