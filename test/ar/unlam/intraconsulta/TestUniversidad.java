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
		Alumno alumno = new Alumno(universidad.getIdAlumno(), "Juan", "Perez", 40123456, LocalDate.of(1997, 3, 20), LocalDate.of(2023, 1, 31));
		Aula aula = new Aula(261, 50);
		
		comision.asignarDiaDeCurso(DiasSemana.MARTES);
		
		assertTrue(universidad.agregarCicloLectivo(cicloLectivo));
		assertTrue(universidad.agregarAula(aula));
		assertTrue(universidad.agregarMateria(materia));
		assertTrue(universidad.agregarComision(comision));
		assertTrue(universidad.agregarAlumno(alumno));
		assertTrue(universidad.asignarAulaAComision(comision.getId(), aula.getNumeroAula()));
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
		Alumno alumno = new Alumno(universidad.getIdAlumno(), "Juan", "Perez", 40123456, LocalDate.of(1997, 3, 20), LocalDate.of(2023, 1, 31));
		
		comision.asignarDiaDeCurso(DiasSemana.MARTES);
		
		assertTrue(universidad.agregarCicloLectivo(cicloLectivo));
		assertTrue(universidad.agregarMateria(materia));
		assertTrue(universidad.agregarComision(comision));
		assertTrue(universidad.agregarAula(new Aula(123, 60)));
		assertTrue(universidad.asignarAulaAComision(comision.getId(), 123));
		assertTrue(universidad.agregarAlumno(alumno));
		assertFalse(universidad.inscribirAlumnoAComision(alumno.getDni(), comision.getId()));
		assertEquals(0, universidad.getListaInscripciones().size());
	}
	
	@Test
	public void queSePuedaInscribirAlumnoAComisionConCorrelativasAprobadas() {
		Universidad universidad = new Universidad("UNLaM");
		CicloLectivo cicloLectivo1 = new CicloLectivo(universidad.getIdCicloLectivo(), LocalDate.of(2023, 12, 3),
				LocalDate.of(2023, 12, 23), LocalDate.of(2023, 9, 18), LocalDate.of(2023, 12, 2));
		CicloLectivo cicloLectivo2 = new CicloLectivo(universidad.getIdCicloLectivo(), LocalDate.of(2024, 1, 31),
				LocalDate.of(2024, 3, 2), LocalDate.of(2023, 9, 18), LocalDate.of(2023, 12, 2));
		Aula aula1 = new Aula(261, 50);
		Aula aula2 = new Aula(123, 60);
		Aula aula3 = new Aula(317, 60);
		
		assertTrue(universidad.agregarCicloLectivo(cicloLectivo1));
		assertTrue(universidad.agregarCicloLectivo(cicloLectivo2));
		assertTrue(universidad.agregarAula(aula1));
		assertTrue(universidad.agregarAula(aula2));
		assertTrue(universidad.agregarAula(aula3));
		
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
		
		assertTrue(universidad.buscarMateriaPorId(nuevaMateria.getIdMateria()).getListaCorrelativas().containsAll(listaCorrelativas));
		
		//comisiones y alumno
		Comision comisionCorrelativa1 = new Comision(universidad.getIdComision(), correlativa1, 1234, cicloLectivo1, Turnos.MANIANA);
		Comision comisionCorrelativa2 = new Comision(universidad.getIdComision(), correlativa2, 5678, cicloLectivo1, Turnos.MANIANA);
		Comision comisionMateria = new Comision(universidad.getIdComision(), nuevaMateria, 9123, cicloLectivo2, Turnos.MANIANA);
		Alumno alumno = new Alumno(universidad.getIdAlumno(), "Juan", "Perez", 40123456, LocalDate.of(1997, 3, 20), LocalDate.of(2023, 1, 31));
		
		assertTrue(universidad.agregarAlumno(alumno));
		
		assertTrue(comisionCorrelativa1.asignarDiaDeCurso(DiasSemana.MARTES));
		assertTrue(comisionCorrelativa1.asignarDiaDeCurso(DiasSemana.VIERNES));
		assertTrue(universidad.agregarComision(comisionCorrelativa1));
		assertTrue(universidad.asignarAulaAComision(comisionCorrelativa1.getId(), aula1.getNumeroAula()));
		assertTrue(universidad.inscribirAlumnoAComision(alumno.getDni(), comisionCorrelativa1.getId()));
		
		assertTrue(universidad.registrarNota(comisionCorrelativa1.getId(), alumno.getDni(), new Nota(7, TipoDeNota.PRIMER_PARCIAL)));
		assertTrue(universidad.registrarNota(comisionCorrelativa1.getId(), alumno.getDni(), new Nota(8, TipoDeNota.SEGUNDO_PARCIAL)));
		assertTrue(universidad.buscarInscripcion(alumno.getDni(), comisionCorrelativa1.getId()).estaAprobada());
		assertTrue(universidad.buscarInscripcion(alumno.getDni(), comisionCorrelativa1.getId()).estaPromocionada());
		
		assertTrue(comisionCorrelativa2.asignarDiaDeCurso(DiasSemana.MIERCOLES));
		assertTrue(universidad.agregarComision(comisionCorrelativa2));
		assertTrue(universidad.asignarAulaAComision(comisionCorrelativa2.getId(), aula2.getNumeroAula()));
		assertTrue(universidad.inscribirAlumnoAComision(alumno.getDni(), comisionCorrelativa2.getId()));
		
		assertTrue(universidad.registrarNota(comisionCorrelativa2.getId(), alumno.getDni(), new Nota(2, TipoDeNota.PRIMER_PARCIAL)));
		assertTrue(universidad.registrarNota(comisionCorrelativa2.getId(), alumno.getDni(), new Nota(7, TipoDeNota.SEGUNDO_PARCIAL)));
		assertEquals(2.0, universidad.buscarInscripcion(alumno.getDni(), comisionCorrelativa2.getId()).obtenerNotaFinal(), 0.01);
		assertFalse(universidad.buscarInscripcion(alumno.getDni(), comisionCorrelativa2.getId()).estaAprobada());
		assertTrue(universidad.registrarNota(comisionCorrelativa2.getId(), alumno.getDni(), new Nota(4, TipoDeNota.RECUPERATORIO_PRIMER_PARCIAL)));
		assertTrue(universidad.buscarInscripcion(alumno.getDni(), comisionCorrelativa2.getId()).estaAprobada());
		assertFalse(universidad.buscarInscripcion(alumno.getDni(), comisionCorrelativa2.getId()).estaPromocionada());
		
		assertTrue(comisionMateria.asignarDiaDeCurso(DiasSemana.MIERCOLES));
		assertTrue(universidad.agregarComision(comisionMateria));
		assertFalse(universidad.asignarAulaAComision(comisionCorrelativa1.getId(), aula1.getNumeroAula()));
		assertTrue(universidad.asignarAulaAComision(comisionMateria.getId(), aula3.getNumeroAula()));
		
		assertEquals(listaCorrelativas, universidad.obtenerMateriasAprobadasParaUnAlumno(alumno.getDni()));
		assertTrue(universidad.inscribirAlumnoAComision(alumno.getDni(), comisionMateria.getId()));
	}
	
	@Test
	public void queNoSePuedaInscribirAlumnoAComisionConCorrelativasNoAprobadas() {
		Universidad universidad = new Universidad("UNLaM");
		CicloLectivo cicloLectivo = new CicloLectivo(universidad.getIdCicloLectivo(), LocalDate.of(2024, 1, 31),
				LocalDate.of(2024, 3, 2), LocalDate.of(2023, 9, 18), LocalDate.of(2023, 12, 31));
		
		assertTrue(universidad.agregarCicloLectivo(cicloLectivo));
		
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
		Alumno alumno = new Alumno(universidad.getIdAlumno(), "Juan", "Perez", 40123456, LocalDate.of(1997, 3, 20), LocalDate.of(2023, 1, 31));
		
		assertTrue(comisionCorrelativa1.asignarDiaDeCurso(DiasSemana.MARTES));
		assertTrue(comisionCorrelativa1.asignarDiaDeCurso(DiasSemana.VIERNES));
		assertTrue(universidad.agregarComision(comisionCorrelativa1));
		assertTrue(comisionCorrelativa2.asignarDiaDeCurso(DiasSemana.MIERCOLES));
		assertTrue(universidad.agregarComision(comisionCorrelativa2));
		assertTrue(comisionMateria.asignarDiaDeCurso(DiasSemana.MIERCOLES));
		assertTrue(universidad.agregarComision(comisionMateria));
		assertTrue(universidad.agregarAula(new Aula(123, 60)));
		assertTrue(universidad.asignarAulaAComision(comisionMateria.getId(), 123));
		assertTrue(universidad.agregarAlumno(alumno));
		
		assertFalse(universidad.inscribirAlumnoAComision(alumno.getDni(), comisionMateria.getId()));
	}
	
	@Test
	public void queNoSePuedaInscribirAlumnoAComisionSuperadaLaCantidadMaximaDeAula() {
		Universidad universidad = new Universidad("UNLaM");
		CicloLectivo cicloLectivo = new CicloLectivo(universidad.getIdCicloLectivo(), LocalDate.of(2024, 1, 31),
				LocalDate.of(2024, 3, 2), LocalDate.of(2023, 9, 18), LocalDate.of(2023, 12, 31));
		Aula aula = new Aula(261, 1);
		Materia materia = new Materia(universidad.getIdMateria(), 2619, "PB1");
		Comision comision = new Comision(universidad.getIdComision(), materia, 2323, cicloLectivo, Turnos.MANIANA);
		Alumno alumno = new Alumno(universidad.getIdAlumno(), "Juan", "Perez", 40123456, LocalDate.of(1997, 3, 20), LocalDate.of(2023, 1, 31));
		Alumno alumno2 = new Alumno(universidad.getIdAlumno(), "Carla", "Rossi", 42548723, LocalDate.of(1999, 8, 15), LocalDate.of(2023, 1, 31));
		
		comision.asignarDiaDeCurso(DiasSemana.MARTES);
		
		assertTrue(universidad.agregarCicloLectivo(cicloLectivo));
		assertTrue(universidad.agregarAula(aula));
		assertTrue(universidad.agregarMateria(materia));
		assertTrue(universidad.agregarComision(comision));
		assertTrue(universidad.agregarAlumno(alumno));
		assertTrue(universidad.asignarAulaAComision(comision.getId(), aula.getNumeroAula()));
		assertTrue(universidad.inscribirAlumnoAComision(alumno.getDni(), comision.getId()));
		assertFalse(universidad.inscribirAlumnoAComision(alumno2.getDni(), comision.getId()));
		assertEquals(1, universidad.getListaInscripciones().size());
	}
	
	@Test
	public void queNoSePuedaInscribirAlumnoAComisionSiYaEstaInscriptoAOtraComisionEnMismoDiaYTurno() {
		Universidad universidad = new Universidad("UNLaM");
		CicloLectivo cicloLectivo = new CicloLectivo(universidad.getIdCicloLectivo(), LocalDate.of(2024, 1, 31),
				LocalDate.of(2024, 3, 2), LocalDate.of(2023, 9, 18), LocalDate.of(2023, 12, 31));
		
		assertTrue(universidad.agregarCicloLectivo(cicloLectivo));
		
		//materia
		Materia materia1 = new Materia(universidad.getIdMateria(), 2619, "PB1");
		Materia materia2 = new Materia(universidad.getIdMateria(), 2620, "IG");
		
		assertTrue(universidad.agregarMateria(materia1));
		assertTrue(universidad.agregarMateria(materia2));
		
		//comisiones y alumno
		Comision comision1 = new Comision(universidad.getIdComision(), materia1, 1234, cicloLectivo, Turnos.MANIANA);
		Comision comision2 = new Comision(universidad.getIdComision(), materia2, 5678, cicloLectivo, Turnos.MANIANA);
		Alumno alumno = new Alumno(universidad.getIdAlumno(), "Juan", "Perez", 40123456, LocalDate.of(1997, 3, 20), LocalDate.of(2023, 1, 31));
		
		assertTrue(universidad.agregarAlumno(alumno));
		
		assertTrue(comision1.asignarDiaDeCurso(DiasSemana.MARTES));
		assertTrue(comision1.asignarDiaDeCurso(DiasSemana.VIERNES));
		assertTrue(universidad.agregarComision(comision1));
		assertTrue(comision2.asignarDiaDeCurso(DiasSemana.VIERNES));
		assertTrue(universidad.agregarComision(comision2));
		assertTrue(universidad.agregarAula(new Aula(123, 60)));
		assertTrue(universidad.agregarAula(new Aula(321, 60)));
		assertTrue(universidad.asignarAulaAComision(comision1.getId(), 123));
		assertTrue(universidad.asignarAulaAComision(comision2.getId(), 321));
		
		assertTrue(universidad.inscribirAlumnoAComision(alumno.getDni(), comision1.getId()));
		assertFalse(universidad.inscribirAlumnoAComision(alumno.getDni(), comision2.getId()));
	}
	
	@Test
	public void queNoSePuedaInscribirAUnAlumnoAUnaComisionDeUnaMateriaYaAprobada() {
		Universidad universidad = new Universidad("UNLaM");
		CicloLectivo cicloLectivo1 = new CicloLectivo(universidad.getIdCicloLectivo(), LocalDate.of(2023, 12, 3),
				LocalDate.of(2023, 12, 23), LocalDate.of(2023, 9, 18), LocalDate.of(2023, 12, 2));
		CicloLectivo cicloLectivo2 = new CicloLectivo(universidad.getIdCicloLectivo(), LocalDate.of(2024, 1, 31),
				LocalDate.of(2024, 3, 2), LocalDate.of(2023, 9, 18), LocalDate.of(2023, 12, 2));
		Aula aula = new Aula(261, 50);
		
		assertTrue(universidad.agregarCicloLectivo(cicloLectivo1));
		assertTrue(universidad.agregarCicloLectivo(cicloLectivo2));
		assertTrue(universidad.agregarAula(aula));
		
		//materia
		Materia materia = new Materia(universidad.getIdMateria(), 2619, "PB1");
		
		assertTrue(universidad.agregarMateria(materia));
		
		//comisiones y alumno
		Comision comision1 = new Comision(universidad.getIdComision(), materia, 1234, cicloLectivo1, Turnos.MANIANA);
		Comision comision2 = new Comision(universidad.getIdComision(), materia, 5678, cicloLectivo2, Turnos.MANIANA);
		Alumno alumno = new Alumno(universidad.getIdAlumno(), "Juan", "Perez", 40123456, LocalDate.of(1997, 3, 20), LocalDate.of(2023, 1, 31));
		
		assertTrue(universidad.agregarAlumno(alumno));
		
		assertTrue(comision1.asignarDiaDeCurso(DiasSemana.MARTES));
		assertTrue(comision1.asignarDiaDeCurso(DiasSemana.VIERNES));
		assertTrue(universidad.agregarComision(comision1));
		assertTrue(universidad.asignarAulaAComision(comision1.getId(), aula.getNumeroAula()));
		assertTrue(universidad.inscribirAlumnoAComision(alumno.getDni(), comision1.getId()));
		
		assertTrue(universidad.registrarNota(comision1.getId(), alumno.getDni(), new Nota(7, TipoDeNota.PRIMER_PARCIAL)));
		assertTrue(universidad.registrarNota(comision1.getId(), alumno.getDni(), new Nota(8, TipoDeNota.SEGUNDO_PARCIAL)));
		assertTrue(universidad.buscarInscripcion(alumno.getDni(), comision1.getId()).estaAprobada());
		assertTrue(universidad.buscarInscripcion(alumno.getDni(), comision1.getId()).estaPromocionada());
		
		assertTrue(comision2.asignarDiaDeCurso(DiasSemana.MARTES));
		assertTrue(comision2.asignarDiaDeCurso(DiasSemana.VIERNES));
		assertTrue(universidad.agregarComision(comision2));
		assertTrue(universidad.asignarAulaAComision(comision2.getId(), aula.getNumeroAula()));
		assertFalse(universidad.inscribirAlumnoAComision(alumno.getDni(), comision2.getId()));
	}
	
	@Test
	public void queNoSePuedaCalificarConSieteOMasSiLasCorrelativasNoEstanPromocionadas() {
		Universidad universidad = new Universidad("UNLaM");
		CicloLectivo cicloLectivo1 = new CicloLectivo(universidad.getIdCicloLectivo(), LocalDate.of(2023, 12, 3),
				LocalDate.of(2023, 12, 23), LocalDate.of(2023, 9, 18), LocalDate.of(2023, 12, 2));
		CicloLectivo cicloLectivo2 = new CicloLectivo(universidad.getIdCicloLectivo(), LocalDate.of(2024, 1, 31),
				LocalDate.of(2024, 3, 2), LocalDate.of(2023, 9, 18), LocalDate.of(2023, 12, 2));
		Aula aula1 = new Aula(261, 50);
		Aula aula2 = new Aula(123, 60);
		Aula aula3 = new Aula(317, 60);
		
		assertTrue(universidad.agregarCicloLectivo(cicloLectivo1));
		assertTrue(universidad.agregarCicloLectivo(cicloLectivo2));
		assertTrue(universidad.agregarAula(aula1));
		assertTrue(universidad.agregarAula(aula2));
		assertTrue(universidad.agregarAula(aula3));
		
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
		
		assertTrue(universidad.buscarMateriaPorId(nuevaMateria.getIdMateria()).getListaCorrelativas().containsAll(listaCorrelativas));
		
		//comisiones y alumno
		Comision comisionCorrelativa1 = new Comision(universidad.getIdComision(), correlativa1, 1234, cicloLectivo1, Turnos.MANIANA);
		Comision comisionCorrelativa2 = new Comision(universidad.getIdComision(), correlativa2, 5678, cicloLectivo1, Turnos.MANIANA);
		Comision comisionMateria = new Comision(universidad.getIdComision(), nuevaMateria, 9123, cicloLectivo2, Turnos.MANIANA);
		Alumno alumno = new Alumno(universidad.getIdAlumno(), "Juan", "Perez", 40123456, LocalDate.of(1997, 3, 20), LocalDate.of(2023, 1, 31));
		
		assertTrue(universidad.agregarAlumno(alumno));
		
		assertTrue(comisionCorrelativa1.asignarDiaDeCurso(DiasSemana.MARTES));
		assertTrue(comisionCorrelativa1.asignarDiaDeCurso(DiasSemana.VIERNES));
		assertTrue(universidad.agregarComision(comisionCorrelativa1));
		assertTrue(universidad.asignarAulaAComision(comisionCorrelativa1.getId(), aula1.getNumeroAula()));
		assertTrue(universidad.inscribirAlumnoAComision(alumno.getDni(), comisionCorrelativa1.getId()));
		
		assertTrue(universidad.registrarNota(comisionCorrelativa1.getId(), alumno.getDni(), new Nota(7, TipoDeNota.PRIMER_PARCIAL)));
		assertTrue(universidad.registrarNota(comisionCorrelativa1.getId(), alumno.getDni(), new Nota(8, TipoDeNota.SEGUNDO_PARCIAL)));
		assertTrue(universidad.buscarInscripcion(alumno.getDni(), comisionCorrelativa1.getId()).estaAprobada());
		assertTrue(universidad.buscarInscripcion(alumno.getDni(), comisionCorrelativa1.getId()).estaPromocionada());
		
		assertTrue(comisionCorrelativa2.asignarDiaDeCurso(DiasSemana.MIERCOLES));
		assertTrue(universidad.agregarComision(comisionCorrelativa2));
		assertTrue(universidad.asignarAulaAComision(comisionCorrelativa2.getId(), aula2.getNumeroAula()));
		assertTrue(universidad.inscribirAlumnoAComision(alumno.getDni(), comisionCorrelativa2.getId()));
		
		assertTrue(universidad.registrarNota(comisionCorrelativa2.getId(), alumno.getDni(), new Nota(2, TipoDeNota.PRIMER_PARCIAL)));
		assertTrue(universidad.registrarNota(comisionCorrelativa2.getId(), alumno.getDni(), new Nota(7, TipoDeNota.SEGUNDO_PARCIAL)));
		assertTrue(universidad.registrarNota(comisionCorrelativa2.getId(), alumno.getDni(), new Nota(4, TipoDeNota.RECUPERATORIO_PRIMER_PARCIAL)));
		assertTrue(universidad.buscarInscripcion(alumno.getDni(), comisionCorrelativa2.getId()).estaAprobada());
		assertFalse(universidad.buscarInscripcion(alumno.getDni(), comisionCorrelativa2.getId()).estaPromocionada());
		
		assertTrue(comisionMateria.asignarDiaDeCurso(DiasSemana.MIERCOLES));
		assertTrue(universidad.agregarComision(comisionMateria));
		assertTrue(universidad.asignarAulaAComision(comisionMateria.getId(), aula3.getNumeroAula()));
		assertTrue(universidad.inscribirAlumnoAComision(alumno.getDni(), comisionMateria.getId()));
		
		assertTrue(universidad.registrarNota(comisionMateria.getId(), alumno.getDni(), new Nota(5, TipoDeNota.PRIMER_PARCIAL)));
		assertFalse(universidad.registrarNota(comisionMateria.getId(), alumno.getDni(), new Nota(7, TipoDeNota.SEGUNDO_PARCIAL)));
		assertTrue(universidad.registrarNota(comisionMateria.getId(), alumno.getDni(), new Nota(6, TipoDeNota.SEGUNDO_PARCIAL)));
		assertTrue(universidad.buscarInscripcion(alumno.getDni(), comisionMateria.getId()).estaAprobada());
		assertFalse(universidad.buscarInscripcion(alumno.getDni(), comisionMateria.getId()).estaPromocionada());
	}
	
	/*--------------------------------------------------PROFECOMISION--------------------------------------------------*/
	@Test
	public void queSePuedaAsignarUnProfesorAUnaComision() {
		Universidad universidad = new Universidad("UNLaM");
		CicloLectivo cicloLectivo = new CicloLectivo(universidad.getIdCicloLectivo(), LocalDate.of(2024, 1, 31),
				LocalDate.of(2024, 3, 2), LocalDate.of(2023, 9, 18), LocalDate.of(2023, 12, 2));
		Materia materia = new Materia(universidad.getIdMateria(), 2619, "PB1");
		Comision comision = new Comision(universidad.getIdComision(), materia, 2323, cicloLectivo, Turnos.MANIANA);
		Alumno alumno = new Alumno(universidad.getIdAlumno(), "Juan", "Perez", 40123456, LocalDate.of(1997, 3, 20), LocalDate.of(2023, 1, 31));
		Profesor profesor = new Profesor(universidad.getIdProfesor(), "Jose", "Gomez", 20123456);
		Aula aula = new Aula(261, 50);
		
		comision.asignarDiaDeCurso(DiasSemana.MARTES);
		
		assertTrue(universidad.agregarCicloLectivo(cicloLectivo));
		assertTrue(universidad.agregarAula(aula));
		assertTrue(universidad.agregarMateria(materia));
		assertTrue(universidad.agregarComision(comision));
		assertTrue(universidad.agregarProfesor(profesor));
		assertTrue(universidad.agregarAlumno(alumno));
		
		assertTrue(universidad.asignarAulaAComision(comision.getId(), aula.getNumeroAula()));
		assertTrue(universidad.inscribirAlumnoAComision(alumno.getDni(), comision.getId()));
		assertTrue(universidad.asignarProfesorAComision(comision.getId(), profesor.getDni()));

		assertEquals(1, universidad.getListaAsignacionesProfesorComision().size());
		assertEquals(1, universidad.obtenerListaDeProfesoresDeUnaComision(comision.getId()).size());
	}
	
	@Test
	public void queNoSePuedaAsignarUnProfesorAUnaComisionEnDiaYTurnoOcupados() {
		Universidad universidad = new Universidad("UNLaM");
		CicloLectivo cicloLectivo = new CicloLectivo(universidad.getIdCicloLectivo(), LocalDate.of(2024, 1, 31),
				LocalDate.of(2024, 3, 2), LocalDate.of(2023, 9, 18), LocalDate.of(2023, 12, 2));
		Materia materia1 = new Materia(universidad.getIdMateria(), 2619, "PB1");
		Materia materia2 = new Materia(universidad.getIdMateria(), 2620, "IG");
		Comision comision1 = new Comision(universidad.getIdComision(), materia1, 2323, cicloLectivo, Turnos.MANIANA);
		Comision comision2 = new Comision(universidad.getIdComision(), materia2, 2324, cicloLectivo, Turnos.MANIANA);
		Alumno alumno1 = new Alumno(universidad.getIdAlumno(), "Juan", "Perez", 40123456, LocalDate.of(1997, 3, 20), LocalDate.of(2023, 1, 31));
		Alumno alumno2 = new Alumno(universidad.getIdAlumno(), "Julia", "Arias", 40849758, LocalDate.of(1997, 3, 20), LocalDate.of(2023, 1, 31));
		Profesor profesor = new Profesor(universidad.getIdProfesor(), "Jose", "Gomez", 20123456);
		Aula aula1 = new Aula(261, 50);
		Aula aula2 = new Aula(123, 60);
		
		comision1.asignarDiaDeCurso(DiasSemana.MARTES);
		comision2.asignarDiaDeCurso(DiasSemana.MARTES);
		
		assertTrue(universidad.agregarCicloLectivo(cicloLectivo));
		assertTrue(universidad.agregarAula(aula1));
		assertTrue(universidad.agregarAula(aula2));
		assertTrue(universidad.agregarMateria(materia1));
		assertTrue(universidad.agregarMateria(materia2));
		assertTrue(universidad.agregarComision(comision1));
		assertTrue(universidad.agregarComision(comision2));
		assertTrue(universidad.agregarProfesor(profesor));
		assertTrue(universidad.agregarAlumno(alumno1));
		assertTrue(universidad.agregarAlumno(alumno2));
		
		assertTrue(universidad.asignarAulaAComision(comision1.getId(), aula1.getNumeroAula()));
		assertTrue(universidad.asignarAulaAComision(comision2.getId(), aula2.getNumeroAula()));
		assertTrue(universidad.inscribirAlumnoAComision(alumno1.getDni(), comision1.getId()));
		assertTrue(universidad.inscribirAlumnoAComision(alumno2.getDni(), comision2.getId()));
		
		assertTrue(universidad.asignarProfesorAComision(comision1.getId(), profesor.getDni()));
		assertFalse(universidad.asignarProfesorAComision(comision2.getId(), profesor.getDni()));

		assertEquals(1, universidad.getListaAsignacionesProfesorComision().size());
		assertEquals(1, universidad.obtenerListaDeProfesoresDeUnaComision(comision1.getId()).size());
	}
	
	@Test
	public void queUnProfesorNoPuedaSerAsignadoDosVecesAUnaComision() {
		Universidad universidad = new Universidad("UNLaM");
		CicloLectivo cicloLectivo = new CicloLectivo(universidad.getIdCicloLectivo(), LocalDate.of(2024, 1, 31),
				LocalDate.of(2024, 3, 2), LocalDate.of(2023, 9, 18), LocalDate.of(2023, 12, 2));
		Materia materia = new Materia(universidad.getIdMateria(), 2619, "PB1");
		Comision comision = new Comision(universidad.getIdComision(), materia, 2323, cicloLectivo, Turnos.MANIANA);
		Alumno alumno = new Alumno(universidad.getIdAlumno(), "Juan", "Perez", 40123456, LocalDate.of(1997, 3, 20), LocalDate.of(2023, 1, 31));
		Profesor profesor = new Profesor(universidad.getIdProfesor(), "Jose", "Gomez", 20123456);
		Aula aula = new Aula(261, 50);
		
		comision.asignarDiaDeCurso(DiasSemana.MARTES);
		
		assertTrue(universidad.agregarCicloLectivo(cicloLectivo));
		assertTrue(universidad.agregarAula(aula));
		assertTrue(universidad.agregarMateria(materia));
		assertTrue(universidad.agregarComision(comision));
		assertTrue(universidad.agregarProfesor(profesor));
		assertTrue(universidad.agregarAlumno(alumno));
		
		assertTrue(universidad.asignarAulaAComision(comision.getId(), aula.getNumeroAula()));
		assertTrue(universidad.inscribirAlumnoAComision(alumno.getDni(), comision.getId()));
		assertTrue(universidad.asignarProfesorAComision(comision.getId(), profesor.getDni()));
		assertFalse(universidad.asignarProfesorAComision(comision.getId(), profesor.getDni()));

		assertEquals(1, universidad.getListaAsignacionesProfesorComision().size());
		assertEquals(1, universidad.obtenerListaDeProfesoresDeUnaComision(comision.getId()).size());
	}
	
	@Test
	public void queSePuedaAsignarDosProfesoresAUnaComision() {
		Universidad universidad = new Universidad("UNLaM");
		CicloLectivo cicloLectivo = new CicloLectivo(universidad.getIdCicloLectivo(), LocalDate.of(2024, 1, 31),
				LocalDate.of(2024, 3, 2), LocalDate.of(2023, 9, 18), LocalDate.of(2023, 12, 2));
		Materia materia = new Materia(universidad.getIdMateria(), 2619, "PB1");
		Comision comision = new Comision(universidad.getIdComision(), materia, 2323, cicloLectivo, Turnos.MANIANA);
		LocalDate fechaNacimiento = LocalDate.of(1997, 3, 20), fechaIngreso = LocalDate.of(2023, 1, 31);
		Integer documento = 40123456;
		String nombre = "a", apellido = "b";
		Profesor profesor1 = new Profesor(universidad.getIdProfesor(), "Jose", "Gomez", 20123456);
		Profesor profesor2 = new Profesor(universidad.getIdProfesor(), "Juan", "Medrano", 21548768);
		
		Aula aula = new Aula(261, 50);
		ArrayList<Alumno> aux = new ArrayList<Alumno>();
		
		comision.asignarDiaDeCurso(DiasSemana.MARTES);
		
		assertTrue(universidad.agregarCicloLectivo(cicloLectivo));
		assertTrue(universidad.agregarAula(aula));
		assertTrue(universidad.agregarMateria(materia));
		assertTrue(universidad.agregarComision(comision));
		assertTrue(universidad.agregarProfesor(profesor1));
		assertTrue(universidad.agregarProfesor(profesor2));
		assertTrue(universidad.asignarAulaAComision(comision.getId(), aula.getNumeroAula()));

		for (int i = 0; i < 25; i++) {
			aux.add(new Alumno(universidad.getIdAlumno(), nombre, apellido, documento++, fechaNacimiento, fechaIngreso));
		}
		
		for (Alumno alumno : aux) {
			assertTrue(universidad.agregarAlumno(alumno));
			assertTrue(universidad.inscribirAlumnoAComision(alumno.getDni(), comision.getId()));
		}
		
		assertTrue(universidad.asignarProfesorAComision(comision.getId(), profesor1.getDni()));
		assertTrue(universidad.asignarProfesorAComision(comision.getId(), profesor2.getDni()));

		assertEquals(2, universidad.getListaAsignacionesProfesorComision().size());
		assertEquals(2, universidad.obtenerListaDeProfesoresDeUnaComision(comision.getId()).size());
	}

	/*--------------------------------------------------AULA--------------------------------------------------*/

	/*--------------------------------------------------NOTA--------------------------------------------------*/
}
