package ar.unlam.intraconsulta;

public class Profesor {

	private Integer legajo;
	private String nombre;
	private String apellido;
	private Integer dni;

	public Profesor(Integer legajo, String nombre, String apellido, Integer dni) {
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
	public String toString() {
		return apellido + ", " + nombre;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.legajo.equals(((Profesor)obj).getLegajo());
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

}
