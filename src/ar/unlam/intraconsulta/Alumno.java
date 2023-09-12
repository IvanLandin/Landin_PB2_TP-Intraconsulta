package ar.unlam.intraconsulta;

public class Alumno {

	private Integer legajo;
	private String nombre;
	private String apellido;
	private Integer dni;

	public Alumno(Integer legajo, String nombre, String apellido, Integer dni) {
		this.legajo = legajo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
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

	public Integer getDni() {
		return dni;
	}

	@Override
	public boolean equals(Object obj) {
		return this.legajo.equals(((Alumno)obj).getLegajo());
	}
	
	@Override
	public String toString() {
		return apellido + ", " + nombre;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
}
