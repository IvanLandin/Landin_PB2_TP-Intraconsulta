package ar.unlam.intraconsulta;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class TestAlumno {

	@Test
	public void queSePuedaInstanciarUnAlumno() {
		LocalDate fechaNacimiento = LocalDate.of(1997, 4, 2);
		LocalDate fechaIngreso = LocalDate.of(2023, 4, 3);
		Alumno alumno = new Alumno(1, "Ivan", "Landin", 40193158, fechaNacimiento, fechaIngreso);
		
		assertNotNull(alumno);
	}
	
	@Test
	public void queSePuedaCambiarNombreYApellidoDeUnAlumno() {
		LocalDate fechaNacimiento = LocalDate.of(1997, 4, 2);
		LocalDate fechaIngreso = LocalDate.of(2023, 4, 3);
		Alumno alumno = new Alumno(1, "Ivan", "Landin", 40193158, fechaNacimiento, fechaIngreso);
		String nuevoNombre = "Lautaro", nuevoApellido = "Stier";
		
		alumno.setNombre(nuevoNombre);
		alumno.setApellido(nuevoApellido);
		
		assertNotNull(alumno);
		assertEquals(nuevoNombre, alumno.getNombre());
		assertEquals(nuevoApellido, alumno.getApellido());
	}
	
	@Test
	public void queSePuedaDiferenciarDosAlumnosPorLegajo() {
		LocalDate fechaNacimiento = LocalDate.of(1997, 4, 2);
		LocalDate fechaIngreso = LocalDate.of(2023, 4, 3);
		Alumno alumno1 = new Alumno(1, "Ivan", "Landin", 40193158, fechaNacimiento, fechaIngreso);
		Alumno alumno2 = new Alumno(2, "Ivan", "Landin", 40193158, fechaNacimiento, fechaIngreso);
		
		assertNotEquals(alumno1, alumno2);
	}
	
	@Test
	public void queSePuedaCompararDosAlumnosPorLegajo() {
		LocalDate fechaNacimiento1 = LocalDate.of(1997, 4, 2);
		LocalDate fechaNacimiento2 = LocalDate.of(2002, 4, 22);
		LocalDate fechaIngreso1 = LocalDate.of(2023, 4, 3);
		LocalDate fechaIngreso2 = LocalDate.of(2023, 4, 3);
		Alumno alumno1 = new Alumno(1, "Ivan", "Landin", 40193158, fechaNacimiento1, fechaIngreso1);
		Alumno alumno2 = new Alumno(1, "Milagros", "Maldonado", 44182217, fechaNacimiento2, fechaIngreso2);
		
		assertEquals(alumno1, alumno2);
	}

}
