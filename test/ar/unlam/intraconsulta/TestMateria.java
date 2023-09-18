package ar.unlam.intraconsulta;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestMateria {

	@Test
	public void queSePuedaInstanciarUnaMateriaSinCorrelativas() {
		Materia materia = new Materia(2623, "Programacion Basica 2");
		
		assertNotNull(materia);
	}
	
	@Test
	public void queSePuedaInstanciarUnaMateriaConCorrelativas() {
		ArrayList<Materia> listaCorrelativas = new ArrayList<Materia>();
		listaCorrelativas.add(new Materia(2619, "Programacion Basica 1"));
		listaCorrelativas.add(new Materia(2620, "Informatica General"));
		Materia materia = new Materia(2623, "Programacion Basica 2", listaCorrelativas);
		
		assertNotNull(materia);
		assertEquals(listaCorrelativas, materia.getListaCorrelativas());
		assertEquals(2, materia.getListaCorrelativas().size());
	}
	
	@Test
	public void queSePuedaObtenerCodigoYNombreDeUnaMateria() {
		ArrayList<Materia> listaCorrelativas = new ArrayList<Materia>();
		listaCorrelativas.add(new Materia(2619, "Programacion Basica 1"));
		listaCorrelativas.add(new Materia(2620, "Informatica General"));
		Materia materia = new Materia(2623, "Programacion Basica 2", listaCorrelativas);
		String valorEsperado = materia.getCodigoMateria() + " - " + materia.getNombreMateria();
		
		assertNotNull(materia);
		assertEquals(valorEsperado, materia.toString());
	}

}
