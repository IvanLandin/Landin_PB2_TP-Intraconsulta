package ar.unlam.intraconsulta;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestMateria {

	@Test
	public void queSePuedaInstanciarUnaMateriaSinCorrelativas() {
		Materia materia = new Materia(1, 2623, "Programacion Basica 2");
		
		assertNotNull(materia);
	}
	
	@Test
	public void queSePuedaInstanciarUnaMateriaConCorrelativas() {
		ArrayList<Materia> listaMateriasCorrelativas = new ArrayList<Materia>();
		listaMateriasCorrelativas.add(new Materia(1, 2619, "Programacion Basica 1"));
		listaMateriasCorrelativas.add(new Materia(2, 2620, "Informatica General"));
		
		Materia materia = new Materia(3, 2623, "Programacion Basica 2");
		
		for (int i = 0; i < listaMateriasCorrelativas.size(); i++) {
			assertTrue(materia.agregarMateriaCorrelativa(listaMateriasCorrelativas.get(i)));
		}
		
		assertEquals(2, materia.getListaCorrelativas().size());
		for (int i = 0; i < materia.getListaCorrelativas().size(); i++) {
			assertEquals(listaMateriasCorrelativas.get(i), materia.getListaCorrelativas().get(i));
		}
	}
	
	@Test
	public void queSePuedaObtenerCodigoYNombreDeUnaMateria() {
		ArrayList<Materia> listaCorrelativas = new ArrayList<Materia>();
		listaCorrelativas.add(new Materia(1, 2619, "Programacion Basica 1"));
		listaCorrelativas.add(new Materia(2, 2620, "Informatica General"));
		
		Materia materia = new Materia(3, 2623, "Programacion Basica 2");		
		String valorEsperado = "2623 - Programacion Basica 2";
		
		assertEquals(valorEsperado, materia.toString());
	}

}
