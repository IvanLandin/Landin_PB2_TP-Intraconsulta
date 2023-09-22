package ar.unlam.intraconsulta;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestNota {
	
	@Test
	public void queSePuedaAsignarValorYTipoDeNota() {
		Integer valorEsperado = 7;
		Nota nota = new Nota(valorEsperado, TipoDeNota.RECUPERATORIO_PRIMER_PARCIAL);
		
		assertEquals(valorEsperado, nota.getValor());
		assertEquals(TipoDeNota.RECUPERATORIO_PRIMER_PARCIAL, nota.getTipo());
	}

	@Test
	public void queSePuedaCompararDosNotas() {
		Nota nota1 = new Nota(7, TipoDeNota.PRIMER_PARCIAL);
		Nota nota2 = new Nota(7, TipoDeNota.SEGUNDO_PARCIAL);
		
		assertNotEquals(nota1, nota2);
		
		/****************************************************/
		
		nota1 = new Nota(7, TipoDeNota.PRIMER_PARCIAL);
		nota2 = new Nota(7, TipoDeNota.PRIMER_PARCIAL);
		
		assertEquals(nota1, nota2);
	}
	
}
