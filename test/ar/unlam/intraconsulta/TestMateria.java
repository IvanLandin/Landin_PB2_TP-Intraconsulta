package ar.unlam.intraconsulta;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestMateria {

	@Test
	public void queSePuedaInstanciarUnaMateriaSinCorrelativas() {
		Materia materia = new Materia(2326, "Programacion Basica 2");
		
		assertNotNull(materia);
	}
	
	@Test
	public void queSePuedaInstanciarUnaMateriaConCorrelativas() {
		ArrayList<Integer> listaCorrelativas = new ArrayList<Integer>();
		listaCorrelativas.add(2319);
		listaCorrelativas.add(2120);
		Materia materia = new Materia(2326, "Programacion Basica 2", listaCorrelativas);
		
		assertNotNull(materia);
		assertEquals(listaCorrelativas, materia.getListaCorrelativas());
	}
	
	@Test
	public void queSePuedaObtenerCodigoYNombreDeUnaMateria() {
		ArrayList<Integer> listaCorrelativas = new ArrayList<Integer>();
		listaCorrelativas.add(2319);
		listaCorrelativas.add(2120);
		Materia materia = new Materia(2326, "Programacion Basica 2", listaCorrelativas);
		String valorEsperado = materia.getCodigoMateria() + " - " + materia.getNombreMateria();
		
		assertNotNull(materia);
		assertEquals(valorEsperado, materia.toString());
	}

}
