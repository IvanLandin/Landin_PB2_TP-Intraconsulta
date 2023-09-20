package ar.unlam.intraconsulta;

import java.time.LocalDate;

public class Alumno {

	private Integer legajo;
	private String nombre;
	private String apellido;
	private Integer dni;
	private LocalDate fechaNacimiento;
	private LocalDate fechaIngreso;

	public Alumno(Integer legajo, String nombre, String apellido, Integer dni, LocalDate fechaNacimiento, LocalDate fechaIngreso) {
		this.legajo = legajo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.dni = dni;
		this.fechaIngreso = fechaIngreso;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getLegajo() {
		return legajo;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public Integer getDni() {
		return dni;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Alumno)
			return dni.equals(((Alumno)obj).getDni());
		
		return false;
	}
	
	@Override
	public String toString() {
		return apellido + ", " + nombre;
	}
	
	@Override
	public int hashCode() {
		return dni;
	}
}
