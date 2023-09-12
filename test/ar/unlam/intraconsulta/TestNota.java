package ar.unlam.intraconsulta;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestNota {
	
	@Test
	public void queSePuedanCargarDosNotasAprobadas() {
		Integer valorPrimerParcial = 7;
		Integer valorSegundoParcial = 8;
		Nota nota = new Nota();
		
		nota.setPrimerParcial(valorPrimerParcial);
		nota.setSegundoParcial(valorSegundoParcial);
		
		assertFalse(nota.getRecupera());
		assertEquals(7.5, nota.calcularPromedio(), 0.01);
	}

	@Test
	public void queSePuedaEvaluarSiEstaEnCondicionesDeRecuperar() {
		Integer valorPrimerParcial = 7;
		Integer valorSegundoParcial = 4;
		Nota nota = new Nota();
		
		nota.setPrimerParcial(valorPrimerParcial);
		nota.setSegundoParcial(valorSegundoParcial);
		
		assertTrue(nota.getRecupera());
	}

	@Test
	public void queSePuedaRecuperarExitosamenteUnaNota() {
		Integer valorPrimerParcial = 7;
		Integer valorSegundoParcial = 4;
		Nota nota = new Nota();
		
		nota.setPrimerParcial(valorPrimerParcial);
		nota.setSegundoParcial(valorSegundoParcial);
		assertTrue(nota.getRecupera());
		
		nota.setRecuperatorio(8);
		assertFalse(nota.getRecupera());
		assertEquals(7.5, nota.calcularPromedio(), 0.01);
	}
}
