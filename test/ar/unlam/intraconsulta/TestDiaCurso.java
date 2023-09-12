package ar.unlam.intraconsulta;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestDiaCurso {

	@Test
	public void queSePuedaInstanciarUnDia() {
		DiaCurso dia = new DiaCurso(DiasSemana.MARTES.toString(), 8);
		
		assertNotNull(dia);
	}
	
	@Test
	public void queSePuedanCompararDosDiasDeCursoIguales() {
		DiaCurso dia1 = new DiaCurso(DiasSemana.MARTES.toString(), 8);
		DiaCurso dia2 = new DiaCurso(DiasSemana.MARTES.toString(), 8);
		
		assertEquals(dia1, dia2);
	}

}
