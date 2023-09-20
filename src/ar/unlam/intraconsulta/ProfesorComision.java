package ar.unlam.intraconsulta;

public class ProfesorComision {

	private Integer idProfesorComision;
	private Profesor profesor;
	private Comision comision;

	public ProfesorComision(Integer idProfesorComision, Profesor profesor, Comision comision) {
		this.idProfesorComision = idProfesorComision;
		this.profesor = profesor;
		this.comision = comision;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public Integer getIdProfesorComision() {
		return idProfesorComision;
	}

	public Comision getComision() {
		return comision;
	}
	
	@Override
	public String toString() {
		return  "Comision: " + comision + "\tProfesor: " + profesor;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ProfesorComision)
			return this.idProfesorComision.equals(((ProfesorComision)obj).getIdProfesorComision());
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return idProfesorComision;
	}

}
