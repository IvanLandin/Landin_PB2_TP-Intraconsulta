package ar.unlam.intraconsulta;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestAlumno {

	@Test
	public void queSePuedaInstanciarUnAlumno() {
		Alumno alumno = new Alumno(1, "Ivan", "Landin", 40193158);
		
		assertNotNull(alumno);
	}
	
	@Test
	public void queSePuedaCambiarNombreYApellidoDeUnAlumno() {
		Alumno alumno = new Alumno(1, "Ivan", "Landin", 40193158);
		String nuevoNombre = "Lautaro", nuevoApellido = "Stier";
		
		alumno.setNombre(nuevoNombre);
		alumno.setApellido(nuevoApellido);
		
		assertNotNull(alumno);
		assertEquals(nuevoNombre, alumno.getNombre());
		assertEquals(nuevoApellido, alumno.getApellido());
	}
	
	@Test
	public void queSePuedaDiferenciarDosAlumnosPorLegajo() {
		Alumno alumno1 = new Alumno(1, "Ivan", "Landin", 40193158);
		Alumno alumno2 = new Alumno(2, "Ivan", "Landin", 40193158);
		
		assertNotEquals(alumno1, alumno2);
	}
	
	@Test
	public void queSePuedaCompararDosAlumnosPorLegajo() {
		Alumno alumno1 = new Alumno(1, "Ivan", "Landin", 40193158);
		Alumno alumno2 = new Alumno(1, "Milagros", "Maldonado", 44182217);
		
		assertEquals(alumno1, alumno2);
	}

}
