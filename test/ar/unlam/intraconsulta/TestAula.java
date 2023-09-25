package ar.unlam.intraconsulta;

import static org.junit.Assert.*;

import java.time.LocalDate;
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
		CicloLectivo cicloLectivo = new CicloLectivo(1, LocalDate.of(2023, 8, 14),
				LocalDate.of(2023, 12, 2), LocalDate.of(2023, 7, 31), LocalDate.of(2023, 8, 10));
		ArrayList<Aula> listaAulas = new ArrayList<Aula>();
		
		listaAulas.add(new Aula(140, 120));
		listaAulas.add(new Aula(406, 100));
		listaAulas.add(new Aula(123, 60));
		
		assertTrue(listaAulas.get(1).ocupar(DiasSemana.MARTES, Turnos.MANIANA, cicloLectivo));
		
		Integer contadorAulasDisponibles = 0, valorEsperado = 2;
		ArrayList<DiasSemana> dias = new ArrayList<DiasSemana>();
		dias.add(DiasSemana.MARTES);
		dias.add(DiasSemana.MARTES);
		for (Aula aula : listaAulas) {
			if(aula.estaDisponible(dias, Turnos.MANIANA, cicloLectivo))
				contadorAulasDisponibles++;
		}
		
		assertEquals(valorEsperado, contadorAulasDisponibles);
	}

}
