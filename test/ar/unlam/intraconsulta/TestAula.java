package ar.unlam.intraconsulta;

import static org.junit.Assert.*;

import java.util.ArrayList;
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
	
	@Test
	public void queSePuedaVerLasAulasDisponibles() {
		ArrayList<Aula> listaAulas = new ArrayList<Aula>();
		
		listaAulas.add(new Aula(140, 120));
		listaAulas.add(new Aula(406, 100));
		listaAulas.add(new Aula(123, 60));
		
		assertTrue(listaAulas.get(1).ocupar(DiasSemana.MARTES, Turnos.MANIANA));
		
		Integer contadorAulasDisponibles = 0, valorEsperado = 2;
		ArrayList<DiasSemana> dias = new ArrayList<DiasSemana>();
		dias.add(DiasSemana.MARTES);
		dias.add(DiasSemana.MARTES);
		for (Aula aula : listaAulas) {
			if(aula.estaDisponible(dias, Turnos.MANIANA))
				contadorAulasDisponibles++;
		}
		
		assertEquals(valorEsperado, contadorAulasDisponibles);
	}

}
