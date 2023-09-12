package ar.unlam.intraconsulta;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

public class TestComision {

	@Test
	public void queSePuedaInstanciarUnaComisionQueSeCursaUnDiaALaSemana() {
		Materia materia = new Materia(2625, "Base de Datos 1");
		Date fechaInicio = new GregorianCalendar(2023, Calendar.AUGUST, 14).getTime();
		CicloLectivo cl = new CicloLectivo(2023, Cuatrimestres.SEGUNDO, fechaInicio);
		
		Comision comision = new Comision(materia, 2363);
		DiaCurso dc1 = new DiaCurso(DiasSemana.MARTES.toString(), 8);
		
		comision.setDiaCursada(dc1);
		cl.agregarComision(comision);
		//materia.agregarComision(comision);

		assertNotNull(comision);
		assertEquals(1, comision.getDiasCursada().size());
		//assertEquals(1, materia.getListaComisiones().size());
		assertEquals(1, cl.getListaComisiones().size());
	}
	
	@Test
	public void queSePuedaInstanciarUnaComisionQueSeCursaDosDiasALaSemana() {
		Materia materia = new Materia(2625, "Base de Datos 1");
		Date fechaInicio = new GregorianCalendar(2023, Calendar.AUGUST, 14).getTime();
		CicloLectivo cl = new CicloLectivo(2023, Cuatrimestres.SEGUNDO, fechaInicio);
		
		Comision comision = new Comision(materia, 2363);
		DiaCurso dc1 = new DiaCurso(DiasSemana.MARTES.toString(), 8);
		DiaCurso dc2 = new DiaCurso(DiasSemana.SABADO.toString(), 8);
		
		comision.setDiaCursada(dc1);
		comision.setDiaCursada(dc2);
		cl.agregarComision(comision);
		//materia.agregarComision(comision);
		
		assertNotNull(comision);
		assertEquals(2, comision.getDiasCursada().size());
		//assertEquals(1, materia.getListaComisiones().size());
		assertEquals(1, cl.getListaComisiones().size());
	}
	
	@Test
	public void queSePuedaAgregarDosComisionesAUnaMateria() {
		Materia materia = new Materia(2625, "Base de Datos 1");
		Date fechaInicio = new GregorianCalendar(2023, Calendar.AUGUST, 14).getTime();
		CicloLectivo cl = new CicloLectivo(2023, Cuatrimestres.SEGUNDO, fechaInicio);
		
		//comision1
		Comision comision1 = new Comision(materia, 2362);
		DiaCurso dc1 = new DiaCurso(DiasSemana.MARTES.toString(), 8);
		DiaCurso dc2 = new DiaCurso(DiasSemana.SABADO.toString(), 8);
		comision1.setDiaCursada(dc1);
		comision1.setDiaCursada(dc2);
		
		//comision2
		Comision comision2 = new Comision(materia, 2363);
		dc1 = new DiaCurso(DiasSemana.LUNES.toString(), 8);
		dc2 = new DiaCurso(DiasSemana.VIERNES.toString(), 8);
		comision1.setDiaCursada(dc1);
		comision1.setDiaCursada(dc2);
		
		cl.agregarComision(comision1);
		cl.agregarComision(comision2);
		//materia.agregarComision(comision1);
		//materia.agregarComision(comision2);

		//assertEquals(2, materia.getListaComisiones().size());
		assertEquals(2, cl.getListaComisiones().size());
	}

	@Test
	public void queNoSePuedaAgregarDosVecesUnaMismaComision() {
		Materia materia = new Materia(2625, "Base de Datos 1");
		Date fechaInicio = new GregorianCalendar(2023, Calendar.AUGUST, 14).getTime();
		CicloLectivo cl = new CicloLectivo(2023, Cuatrimestres.SEGUNDO, fechaInicio);
		
		Comision comision1 = new Comision(materia, 2362);
		DiaCurso dc1 = new DiaCurso(DiasSemana.MARTES.toString(), 8);
		DiaCurso dc2 = new DiaCurso(DiasSemana.SABADO.toString(), 8);
		
		comision1.setDiaCursada(dc1);
		comision1.setDiaCursada(dc2);
		cl.agregarComision(comision1);
		//materia.agregarComision(comision1);
		//materia.agregarComision(comision1);

		//assertEquals(1, materia.getListaComisiones().size());
		assertEquals(1, cl.getListaComisiones().size());
	}
	
	@Test
	public void queSePuedaAsignarUnAulaAUnaComision() {
		Integer numeroAula = 140;
		Materia materia = new Materia(2625, "Base de Datos 1");
		Date fechaInicio = new GregorianCalendar(2023, Calendar.AUGUST, 14).getTime();
		CicloLectivo cl =  new CicloLectivo(2023, Cuatrimestres.SEGUNDO, fechaInicio);
		
		Comision comision = new Comision(materia, 2363);
		
		comision.setDiaCursada(new DiaCurso(DiasSemana.MARTES.toString(), 8));
		comision.setDiaCursada(new DiaCurso(DiasSemana.SABADO.toString(), 8));
		cl.agregarComision(comision);
		//materia.agregarComision(comision);
		
		//Comision comisionBuscada = materia.buscarComisionPorNumero(2363);
		//comisionBuscada.setAula(new Aula(140, 120));
		comision.setAula(new Aula(140, 120));
		
		assertEquals(2, comision.getDiasCursada().size());
		//assertEquals(1, materia.getListaComisiones().size());
		assertNotNull(comision.getAula());
		assertEquals(numeroAula, comision.getAula().getNumeroAula());
		assertEquals(1, cl.getListaComisiones().size());
	}
	
}
