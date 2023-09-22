package ar.unlam.intraconsulta;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Test;

public class TestUniversidad {

	@Test
	public void queSePuedaInstanciarUnaUniversidad() {
		Universidad universidad = new Universidad("UNLaM");

		assertNotNull(universidad);
	}

	/*--------------------------------------------------CICLO LECTIVO--------------------------------------------------*/

	@Test
	public void queSePuedaAgregarUnCicloLectivo() {
		Universidad universidad = new Universidad("UNLaM");

		LocalDate fechaInicio = LocalDate.of(2023, 8, 14), fechaFinalizacion = LocalDate.of(2023, 12, 2),
				inicioInscripcion = LocalDate.of(2023, 7, 31), finalInscripcion = LocalDate.of(2023, 8, 10);
		CicloLectivo cicloLectivo = new CicloLectivo(universidad.getIdCicloLectivo(), fechaInicio, fechaFinalizacion,
				inicioInscripcion, finalInscripcion);

		assertTrue(universidad.agregarCicloLectivo(cicloLectivo));
		assertEquals(1, universidad.getListaCiclosLectivos().size());
	}

	@Test
	public void queSePuedaAgregarDosCiclosLectivos() {
		Universidad universidad = new Universidad("UNLaM");

		CicloLectivo cicloLectivo1 = new CicloLectivo(universidad.getIdCicloLectivo(), LocalDate.of(2023, 8, 14),
				LocalDate.of(2023, 12, 2), LocalDate.of(2023, 7, 31), LocalDate.of(2023, 8, 10));
		CicloLectivo cicloLectivo2 = new CicloLectivo(universidad.getIdCicloLectivo(), LocalDate.of(2023, 4, 3),
				LocalDate.of(2023, 7, 15), LocalDate.of(2023, 3, 6), LocalDate.of(2023, 3, 13));

		assertTrue(universidad.agregarCicloLectivo(cicloLectivo1));
		assertTrue(universidad.agregarCicloLectivo(cicloLectivo2));
		assertEquals(2, universidad.getListaCiclosLectivos().size());
	}

	@Test
	public void queNoSePuedaAgregarDosCicloLectivoConMismoId() {
		Universidad universidad = new Universidad("UNLaM");

		LocalDate fechaInicio = LocalDate.of(2023, 8, 14), fechaFinalizacion = LocalDate.of(2023, 12, 2),
				inicioInscripcion = LocalDate.of(2023, 7, 31), finalInscripcion = LocalDate.of(2023, 8, 10);
		CicloLectivo cicloLectivo = new CicloLectivo(universidad.getIdCicloLectivo(), fechaInicio, fechaFinalizacion,
				inicioInscripcion, finalInscripcion);

		assertTrue(universidad.agregarCicloLectivo(cicloLectivo));
		assertFalse(universidad.agregarCicloLectivo(cicloLectivo));
		assertEquals(1, universidad.getListaCiclosLectivos().size());
	}

	@Test
	public void queNoSePuedanSuperponerLosIntervalosDeDosCiclosLectivos() {
		Universidad universidad = new Universidad("UNLaM");

		CicloLectivo cicloLectivo1 = new CicloLectivo(universidad.getIdCicloLectivo(), LocalDate.of(2023, 8, 14),
				LocalDate.of(2023, 12, 2), LocalDate.of(2023, 7, 31), LocalDate.of(2023, 8, 10));
		CicloLectivo cicloLectivo2 = new CicloLectivo(universidad.getIdCicloLectivo(), LocalDate.of(2023, 6, 12),
				LocalDate.of(2023, 10, 7), LocalDate.of(2023, 5, 29), LocalDate.of(2023, 6, 7));

		assertTrue(universidad.agregarCicloLectivo(cicloLectivo1));
		assertFalse(universidad.agregarCicloLectivo(cicloLectivo2));
		assertEquals(1, universidad.getListaCiclosLectivos().size());
	}

	/*--------------------------------------------------MATERIA--------------------------------------------------*/

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
	
	@Test
	public void queSePuedaAsignarUnaCorrelativaAUnaMateria() {
		Universidad universidad = new Universidad("UNLaM");
		Materia correlativa = new Materia(universidad.getIdMateria(), 2619, "PB1");
		Materia nuevaMateria = new Materia(universidad.getIdMateria(), 2623, "PB2");
		
		assertTrue(universidad.agregarMateria(correlativa));
		assertTrue(universidad.agregarMateria(nuevaMateria));
		assertTrue(universidad.asignarMateriaCorrelativa(nuevaMateria.getIdMateria(), correlativa.getIdMateria()));
		assertEquals(correlativa, universidad.buscarMateriaPorId(nuevaMateria.getIdMateria()).getListaCorrelativas().get(0));
	}
	
	@Test
	public void queSePuedaAsignarDosCorrelativaAUnaMateria() {
		Universidad universidad = new Universidad("UNLaM");
		
		Materia correlativa1 = new Materia(universidad.getIdMateria(), 2619, "PB1");
		Materia correlativa2 = new Materia(universidad.getIdMateria(), 2620, "IG");
		Materia nuevaMateria = new Materia(universidad.getIdMateria(), 2624, "PW1");
		
		ArrayList<Materia> listaCorrelativas = new ArrayList<Materia>();
		listaCorrelativas.add(correlativa1);
		listaCorrelativas.add(correlativa2);
		
		assertTrue(universidad.agregarMateria(nuevaMateria));
		
		for (Materia correlativa : listaCorrelativas) {
			assertTrue(universidad.agregarMateria(correlativa));
			assertTrue(universidad.asignarMateriaCorrelativa(nuevaMateria.getIdMateria(), correlativa.getIdMateria()));
		}
		
		Integer valorEsperado = 2;
		Integer tam = universidad.buscarMateriaPorId(nuevaMateria.getIdMateria()).getListaCorrelativas().size();
		assertEquals(valorEsperado, tam);
		
		for (int i = 0; i < tam; i++) {
			assertEquals(listaCorrelativas.get(i), universidad.buscarMateriaPorId(nuevaMateria.getIdMateria()).getListaCorrelativas().get(i));
		}
	}
	
	@Test
	public void queNoSePuedaAsignarDosVecesUnaMismaCorrelativaAUnaMateria() {
		Universidad universidad = new Universidad("UNLaM");
		Materia correlativa = new Materia(universidad.getIdMateria(), 2619, "PB1");
		Materia nuevaMateria = new Materia(universidad.getIdMateria(), 2623, "PB2");
		
		assertTrue(universidad.agregarMateria(correlativa));
		assertTrue(universidad.agregarMateria(nuevaMateria));
		assertTrue(universidad.asignarMateriaCorrelativa(nuevaMateria.getIdMateria(), correlativa.getIdMateria()));
		assertEquals(correlativa, universidad.buscarMateriaPorId(nuevaMateria.getIdMateria()).getListaCorrelativas().get(0));
		assertFalse(universidad.asignarMateriaCorrelativa(nuevaMateria.getIdMateria(), correlativa.getIdMateria()));
		assertEquals(1, universidad.buscarMateriaPorId(nuevaMateria.getIdMateria()).getListaCorrelativas().size());
	}
	
	@Test
	public void queNoSePuedaAsignarUnaCorrelativaNoRegistradaAUnaMateria() {
		Universidad universidad = new Universidad("UNLaM");
		Materia correlativa = new Materia(universidad.getIdMateria(), 2619, "PB1");
		Materia nuevaMateria = new Materia(universidad.getIdMateria(), 2623, "PB2");
		
		assertTrue(universidad.agregarMateria(nuevaMateria));
		assertFalse(universidad.asignarMateriaCorrelativa(nuevaMateria.getIdMateria(), correlativa.getIdMateria()));
		assertEquals(0, universidad.buscarMateriaPorId(nuevaMateria.getIdMateria()).getListaCorrelativas().size());
	}
	
	@Test
	public void queSePuedaEliminarUnaCorrelativa() {
		Universidad universidad = new Universidad("UNLaM");
		Materia correlativa = new Materia(universidad.getIdMateria(), 2619, "PB1");
		Materia nuevaMateria = new Materia(universidad.getIdMateria(), 2623, "PB2");
		
		assertTrue(universidad.agregarMateria(correlativa));
		assertTrue(universidad.agregarMateria(nuevaMateria));
		assertTrue(universidad.asignarMateriaCorrelativa(nuevaMateria.getIdMateria(), correlativa.getIdMateria()));
		assertEquals(correlativa, universidad.buscarMateriaPorId(nuevaMateria.getIdMateria()).getListaCorrelativas().get(0));
		assertEquals(1, universidad.buscarMateriaPorId(nuevaMateria.getIdMateria()).getListaCorrelativas().size());
		
		assertTrue(universidad.eliminarCorrelatividad(nuevaMateria.getIdMateria(), correlativa.getIdMateria()));
		assertEquals(0, universidad.buscarMateriaPorId(nuevaMateria.getIdMateria()).getListaCorrelativas().size());
	}

	/*--------------------------------------------------COMISION--------------------------------------------------*/

	@Test
	public void queSePuedaAgregarUnaComision() {
		Universidad universidad = new Universidad("UNLaM");

		LocalDate fechaInicio = LocalDate.of(2023, 8, 14), fechaFinalizacion = LocalDate.of(2023, 12, 2),
				inicioInscripcion = LocalDate.of(2023, 7, 31), finalInscripcion = LocalDate.of(2023, 8, 10);
		CicloLectivo cicloLectivo = new CicloLectivo(universidad.getIdCicloLectivo(), fechaInicio, fechaFinalizacion,
				inicioInscripcion, finalInscripcion);

		Materia nuevaMateria = new Materia(universidad.getIdMateria(), 2619, "PB1");

		Comision nuevaComision = new Comision(universidad.getIdComision(), nuevaMateria, 2623, cicloLectivo,
				Turnos.MANIANA);

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

		CicloLectivo cicloLectivo1 = new CicloLectivo(universidad.getIdCicloLectivo(), LocalDate.of(2023, 8, 14),
				LocalDate.of(2023, 12, 2), LocalDate.of(2023, 7, 31), LocalDate.of(2023, 8, 10));
		CicloLectivo cicloLectivo2 = new CicloLectivo(universidad.getIdCicloLectivo(), LocalDate.of(2023, 4, 3),
				LocalDate.of(2023, 7, 15), LocalDate.of(2023, 3, 6), LocalDate.of(2023, 3, 13));

		Materia nuevaMateria = new Materia(universidad.getIdMateria(), 2619, "PB1");

		Comision nuevaComision1 = new Comision(universidad.getIdComision(), nuevaMateria, 2623, cicloLectivo1,
				Turnos.MANIANA);
		Comision nuevaComision2 = new Comision(universidad.getIdComision(), nuevaMateria, 2623, cicloLectivo2,
				Turnos.MANIANA);

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
		CicloLectivo cicloLectivo = new CicloLectivo(1, fechaInicio, fechaFinalizacion, inicioInscripcion,
				finalInscripcion);

		Materia nuevaMateria = new Materia(1, 2619, "PB1");

		Comision nuevaComision1 = new Comision(universidad.getIdComision(), nuevaMateria, 2623, cicloLectivo,
				Turnos.MANIANA);
		Comision nuevaComision2 = new Comision(universidad.getIdComision(), nuevaMateria, 2623, cicloLectivo,
				Turnos.NOCHE);

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
		CicloLectivo cicloLectivo = new CicloLectivo(universidad.getIdCicloLectivo(), fechaInicio, fechaFinalizacion,
				inicioInscripcion, finalInscripcion);

		Materia nuevaMateria1 = new Materia(universidad.getIdMateria(), 2619, "PB1");
		Materia nuevaMateria2 = new Materia(universidad.getIdMateria(), 2620, "Informatica General");

		Comision nuevaComision1 = new Comision(universidad.getIdComision(), nuevaMateria1, 2623, cicloLectivo,
				Turnos.MANIANA);
		Comision nuevaComision2 = new Comision(universidad.getIdComision(), nuevaMateria2, 2623, cicloLectivo,
				Turnos.MANIANA);

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
		CicloLectivo cicloLectivo = new CicloLectivo(universidad.getIdCicloLectivo(), fechaInicio, fechaFinalizacion,
				inicioInscripcion, finalInscripcion);

		Materia nuevaMateria = new Materia(universidad.getIdMateria(), 2619, "PB1");

		Comision nuevaComision1 = new Comision(universidad.getIdComision(), nuevaMateria, 2623, cicloLectivo,
				Turnos.MANIANA);
		Comision nuevaComision2 = new Comision(universidad.getIdComision(), nuevaMateria, 2623, cicloLectivo,
				Turnos.MANIANA);

		assertTrue(universidad.agregarCicloLectivo(cicloLectivo));
		assertTrue(universidad.agregarMateria(nuevaMateria));

		assertTrue(universidad.agregarComision(nuevaComision1));
		assertFalse(universidad.agregarComision(nuevaComision2));

		assertEquals(1, universidad.getListaMaterias().size());
		assertEquals(1, universidad.getListaCiclosLectivos().size());
		assertEquals(1, universidad.getListaComisiones().size());
	}

	/*--------------------------------------------------ALUMNO--------------------------------------------------*/

	@Test
	public void queSePuedaAgregarUnAlumno() {
		Universidad universidad = new Universidad("UNLaM");

		Alumno alumno = new Alumno(universidad.getIdAlumno(), "Ivan", "Landin", 40193158, LocalDate.of(1997, 4, 2),
				LocalDate.of(2023, 1, 31));

		assertTrue(universidad.agregarAlumno(alumno));
		assertEquals(1, universidad.getListaAlumnos().size());
	}

	@Test
	public void queSePuedaAgregarDosAlumnos() {
		Universidad universidad = new Universidad("UNLaM");

		Alumno alumno1 = new Alumno(universidad.getIdAlumno(), "Gino", "Alvero", 44123456, LocalDate.of(2002, 6, 22),
				LocalDate.of(2023, 1, 31));
		Alumno alumno2 = new Alumno(universidad.getIdAlumno(), "Juan", "Perez", 40123456, LocalDate.of(1997, 5, 6),
				LocalDate.of(2023, 1, 31));

		assertTrue(universidad.agregarAlumno(alumno1));
		assertTrue(universidad.agregarAlumno(alumno2));
		assertEquals(2, universidad.getListaAlumnos().size());
	}

	@Test
	public void queNoSePuedaAgregarDosAlumnosConMismoDni() {
		Universidad universidad = new Universidad("UNLaM");

		Alumno alumno1 = new Alumno(universidad.getIdAlumno(), "Gino", "Alvero", 44123456, LocalDate.of(2002, 6, 22),
				LocalDate.of(2023, 1, 31));
		Alumno alumno2 = new Alumno(universidad.getIdAlumno(), "Juan", "Perez", 44123456, LocalDate.of(1997, 5, 6),
				LocalDate.of(2023, 1, 31));

		assertTrue(universidad.agregarAlumno(alumno1));
		assertFalse(universidad.agregarAlumno(alumno2));
		assertEquals(1, universidad.getListaAlumnos().size());
	}

	/*--------------------------------------------------PROFESOR--------------------------------------------------*/

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

	/*--------------------------------------------------INSCRIPCION--------------------------------------------------*/
	
	@Test
	public void queSePuedaInscribirAlumnoAComisionSinCorrelativas() {
		Universidad universidad = new Universidad("UNLaM");
		CicloLectivo cicloLectivo = new CicloLectivo(universidad.getIdCicloLectivo(), LocalDate.of(2024, 1, 31),
				LocalDate.of(2024, 3, 2), LocalDate.of(2023, 9, 18), LocalDate.of(2023, 12, 31));
		Materia materia = new Materia(universidad.getIdMateria(), 2619, "PB1");
		Comision comision = new Comision(universidad.getIdComision(), materia, 2323, cicloLectivo, Turnos.MANIANA);
		Alumno alumno = new Alumno(universidad.getIdAlumno(), "Juan", "Perez", 40123456, LocalDate.of(2023, 3, 20), LocalDate.of(2023, 1, 31));
		
		assertTrue(universidad.agregarCicloLectivo(cicloLectivo));
		assertTrue(universidad.agregarMateria(materia));
		assertTrue(universidad.agregarComision(comision));
		assertTrue(universidad.agregarAlumno(alumno));
		assertTrue(universidad.inscribirAlumnoAComision(alumno.getDni(), comision.getId()));
		assertEquals(1, universidad.getListaInscripciones().size());
	}
	
	@Test
	public void queNoSePuedaInscribirAlumnoAComisionFueraDeFechaDeInscripcion() {
		Universidad universidad = new Universidad("UNLaM");
		CicloLectivo cicloLectivo = new CicloLectivo(universidad.getIdCicloLectivo(), LocalDate.of(2023, 8, 14),
				LocalDate.of(2023, 12, 2), LocalDate.of(2023, 7, 31), LocalDate.of(2023, 8, 10));
		Materia materia = new Materia(universidad.getIdMateria(), 2619, "PB1");
		Comision comision = new Comision(universidad.getIdComision(), materia, 2323, cicloLectivo, Turnos.MANIANA);
		Alumno alumno = new Alumno(universidad.getIdAlumno(), "Juan", "Perez", 40123456, LocalDate.of(2023, 3, 20), LocalDate.of(2023, 1, 31));
		
		assertTrue(universidad.agregarCicloLectivo(cicloLectivo));
		assertTrue(universidad.agregarMateria(materia));
		assertTrue(universidad.agregarComision(comision));
		assertTrue(universidad.agregarAlumno(alumno));
		assertFalse(universidad.inscribirAlumnoAComision(alumno.getDni(), comision.getId()));
		assertEquals(0, universidad.getListaInscripciones().size());
	}
	
	@Test
	public void queSePuedaInscribirAlumnoAComisionConCorrelativasAprobadas() {
		Universidad universidad = new Universidad("UNLaM");
		CicloLectivo cicloLectivo = new CicloLectivo(universidad.getIdCicloLectivo(), LocalDate.of(2024, 1, 31),
				LocalDate.of(2024, 3, 2), LocalDate.of(2023, 9, 18), LocalDate.of(2023, 12, 31));
		
		//materia
		Materia correlativa1 = new Materia(universidad.getIdMateria(), 2619, "PB1");
		Materia correlativa2 = new Materia(universidad.getIdMateria(), 2620, "IG");
		Materia nuevaMateria = new Materia(universidad.getIdMateria(), 2624, "PW1");
		
		ArrayList<Materia> listaCorrelativas = new ArrayList<Materia>();
		listaCorrelativas.add(correlativa1);
		listaCorrelativas.add(correlativa2);
		
		assertTrue(universidad.agregarMateria(nuevaMateria));
		
		for (Materia correlativa : listaCorrelativas) {
			assertTrue(universidad.agregarMateria(correlativa));
			assertTrue(universidad.asignarMateriaCorrelativa(nuevaMateria.getIdMateria(), correlativa.getIdMateria()));
		}
		
		Integer valorEsperado = 2;
		Integer tam = universidad.buscarMateriaPorId(nuevaMateria.getIdMateria()).getListaCorrelativas().size();
		assertEquals(valorEsperado, tam);
		
		for (int i = 0; i < tam; i++) {
			assertEquals(listaCorrelativas.get(i), universidad.buscarMateriaPorId(nuevaMateria.getIdMateria()).getListaCorrelativas().get(i));
		}
		
		//comisiones y alumno
		Comision comisionCorrelativa1 = new Comision(universidad.getIdComision(), correlativa1, 1234, cicloLectivo, Turnos.MANIANA);
		Comision comisionCorrelativa2 = new Comision(universidad.getIdComision(), correlativa2, 5678, cicloLectivo, Turnos.MANIANA);
		Comision comisionMateria = new Comision(universidad.getIdComision(), nuevaMateria, 9123, cicloLectivo, Turnos.MANIANA);
		Alumno alumno = new Alumno(universidad.getIdAlumno(), "Juan", "Perez", 40123456, LocalDate.of(2023, 3, 20), LocalDate.of(2023, 1, 31));
		
		assertTrue(universidad.agregarComision(comisionCorrelativa1));
		assertTrue(universidad.agregarComision(comisionCorrelativa2));
		assertTrue(universidad.agregarComision(comisionMateria));
		assertTrue(universidad.agregarAlumno(alumno));
		
		assertTrue(universidad.inscribirAlumnoAComision(alumno.getDni(), comisionCorrelativa1.getId()));
		assertTrue(universidad.inscribirAlumnoAComision(alumno.getDni(), comisionCorrelativa2.getId()));

		assertTrue(universidad.registrarNota(comisionCorrelativa1.getId(), alumno.getDni(), new Nota(7, TipoDeNota.PRIMER_PARCIAL)));
		assertTrue(universidad.registrarNota(comisionCorrelativa1.getId(), alumno.getDni(), new Nota(8, TipoDeNota.SEGUNDO_PARCIAL)));
		assertTrue(universidad.buscarInscripcion(alumno.getDni(), comisionCorrelativa1.getId()).estaAprobada());
		assertTrue(universidad.buscarInscripcion(alumno.getDni(), comisionCorrelativa1.getId()).estaPromocionada());
		
		assertTrue(universidad.registrarNota(comisionCorrelativa2.getId(), alumno.getDni(), new Nota(2, TipoDeNota.PRIMER_PARCIAL)));
		assertTrue(universidad.registrarNota(comisionCorrelativa2.getId(), alumno.getDni(), new Nota(7, TipoDeNota.SEGUNDO_PARCIAL)));
		assertFalse(universidad.buscarInscripcion(alumno.getDni(), comisionCorrelativa2.getId()).estaAprobada());
		
		assertTrue(universidad.registrarNota(comisionCorrelativa2.getId(), alumno.getDni(), new Nota(4, TipoDeNota.RECUPERATORIO_PRIMER_PARCIAL)));
		assertTrue(universidad.buscarInscripcion(alumno.getDni(), comisionCorrelativa2.getId()).estaAprobada());
		assertFalse(universidad.buscarInscripcion(alumno.getDni(), comisionCorrelativa2.getId()).estaPromocionada());
		
		assertEquals(listaCorrelativas, universidad.obtenerMateriasAprobadasParaUnAlumno(alumno.getDni()));
		assertTrue(universidad.inscribirAlumnoAComision(alumno.getDni(), comisionMateria.getId()));
	}
	
	@Test
	public void queNoSePuedaInscribirAlumnoAComisionConCorrelativasNoAprobadas() {
		Universidad universidad = new Universidad("UNLaM");
		CicloLectivo cicloLectivo = new CicloLectivo(universidad.getIdCicloLectivo(), LocalDate.of(2024, 1, 31),
				LocalDate.of(2024, 3, 2), LocalDate.of(2023, 9, 18), LocalDate.of(2023, 12, 31));
		
		//materia
		Materia correlativa1 = new Materia(universidad.getIdMateria(), 2619, "PB1");
		Materia correlativa2 = new Materia(universidad.getIdMateria(), 2620, "IG");
		Materia nuevaMateria = new Materia(universidad.getIdMateria(), 2624, "PW1");
		
		ArrayList<Materia> listaCorrelativas = new ArrayList<Materia>();
		listaCorrelativas.add(correlativa1);
		listaCorrelativas.add(correlativa2);
		
		assertTrue(universidad.agregarMateria(nuevaMateria));
		
		for (Materia correlativa : listaCorrelativas) {
			assertTrue(universidad.agregarMateria(correlativa));
			assertTrue(universidad.asignarMateriaCorrelativa(nuevaMateria.getIdMateria(), correlativa.getIdMateria()));
		}
		
		Integer valorEsperado = 2;
		Integer tam = universidad.buscarMateriaPorId(nuevaMateria.getIdMateria()).getListaCorrelativas().size();
		assertEquals(valorEsperado, tam);
		
		for (int i = 0; i < tam; i++) {
			assertEquals(listaCorrelativas.get(i), universidad.buscarMateriaPorId(nuevaMateria.getIdMateria()).getListaCorrelativas().get(i));
		}
		
		//comisiones y alumno
		Comision comisionCorrelativa1 = new Comision(universidad.getIdComision(), correlativa1, 1234, cicloLectivo, Turnos.MANIANA);
		Comision comisionCorrelativa2 = new Comision(universidad.getIdComision(), correlativa2, 5678, cicloLectivo, Turnos.MANIANA);
		Comision comisionMateria = new Comision(universidad.getIdComision(), nuevaMateria, 9123, cicloLectivo, Turnos.MANIANA);
		Alumno alumno = new Alumno(universidad.getIdAlumno(), "Juan", "Perez", 40123456, LocalDate.of(2023, 3, 20), LocalDate.of(2023, 1, 31));
		
		assertTrue(universidad.agregarComision(comisionCorrelativa1));
		assertTrue(universidad.agregarComision(comisionCorrelativa2));
		assertTrue(universidad.agregarComision(comisionMateria));
		assertTrue(universidad.agregarAlumno(alumno));
		
		assertFalse(universidad.inscribirAlumnoAComision(alumno.getDni(), comisionMateria.getId()));
	}
	
	/*--------------------------------------------------PROFECOMISION--------------------------------------------------*/
	@Test
	public void queSePuedaAsignarUnProfesorAUnaComision() {
		Universidad universidad = new Universidad("UNLaM");
		CicloLectivo cicloLectivo = new CicloLectivo(universidad.getIdCicloLectivo(), LocalDate.of(2024, 1, 31),
				LocalDate.of(2024, 3, 2), LocalDate.of(2023, 9, 18), LocalDate.of(2023, 9, 23));
		Materia materia = new Materia(universidad.getIdMateria(), 2619, "PB1");
		Comision comision = new Comision(universidad.getIdComision(), materia, 2323, cicloLectivo, Turnos.MANIANA);
		Alumno alumno = new Alumno(universidad.getIdAlumno(), "Juan", "Perez", 40123456, LocalDate.of(2023, 3, 20), LocalDate.of(2023, 1, 31));
		Profesor profesor = new Profesor(universidad.getIdProfesor(), "Jose", "Gomez", 20123456);
		
		assertTrue(universidad.agregarCicloLectivo(cicloLectivo));
		assertTrue(universidad.agregarMateria(materia));
		assertTrue(universidad.agregarComision(comision));
		assertTrue(universidad.agregarProfesor(profesor));
		assertTrue(universidad.agregarAlumno(alumno));
		assertTrue(universidad.inscribirAlumnoAComision(alumno.getDni(), comision.getId()));
		assertTrue(universidad.asignarProfesorAComision(comision.getId(), profesor.getDni()));

		assertEquals(1, universidad.getListaAsignacionesProfesorComision().size());
		assertEquals(1, universidad.obtenerListaDeProfesoresDeUnaComision(comision.getId()).size());
	}

	/*--------------------------------------------------AULA--------------------------------------------------*/

	/*--------------------------------------------------NOTA--------------------------------------------------*/
}
