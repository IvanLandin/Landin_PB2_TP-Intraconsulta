package ar.unlam.intraconsulta;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestAula {

	@Test
	public void queSePuedaInstanciarUnAula() {
		Aula aula = new Aula(140, 120);
		
		assertNotNull(aula);
	}
	
	@Test
	public void queSePuedaCompararAulasPorNumero() {
		Aula aula1 = new Aula(140, 120);
		Aula aula2 = new Aula(140, 100);

		assertEquals(aula1, aula2);
	}

}
