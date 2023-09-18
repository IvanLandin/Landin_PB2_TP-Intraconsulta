package ar.unlam.intraconsulta;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestDiaCurso {

	@Test
	public void queSePuedaInstanciarUnDia() {
		DiaCurso dia = new DiaCurso(DiasSemana.MARTES, Turnos.MANIANA);
		
		assertNotNull(dia);
	}
	
	@Test
	public void queSePuedanCompararDosDiasDeCursoIguales() {
		DiaCurso dia1 = new DiaCurso(DiasSemana.MARTES, Turnos.MANIANA);
		DiaCurso dia2 = new DiaCurso(DiasSemana.MARTES, Turnos.MANIANA);
		
		assertEquals(dia1, dia2);
	}
	
	@Test
	public void queSePuedaDiferenciarDosDiasDeCursoPorSuTurno() {
		DiaCurso dia1 = new DiaCurso(DiasSemana.MARTES, Turnos.MANIANA);
		DiaCurso dia2 = new DiaCurso(DiasSemana.MARTES, Turnos.NOCHE);
		
		assertNotEquals(dia1, dia2);
	}
	
	@Test
	public void queSePuedaDiferenciarDosDiasDeCursoPorSuDia() {
		DiaCurso dia1 = new DiaCurso(DiasSemana.MARTES, Turnos.MANIANA);
		DiaCurso dia2 = new DiaCurso(DiasSemana.MIERCOLES, Turnos.MANIANA);
		
		assertNotEquals(dia1, dia2);
	}
}
