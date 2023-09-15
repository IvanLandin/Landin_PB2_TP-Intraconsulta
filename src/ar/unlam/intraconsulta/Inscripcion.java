package ar.unlam.intraconsulta;

public class Inscripcion {
	
//	private Integer idInscripcion;
	private Comision comision;
	private Alumno alumno;
	private Nota nota;
	private Boolean aprobada;

	public Inscripcion(Comision comision, Alumno alumno) {
		this.comision = comision;
		this.alumno = alumno;
		this.nota = new Nota();
		this.aprobada = false;
	}
	
//	public Inscripcion(Integer idInscripcion, Comision comision, Alumno alumno) {
//		this.idInscripcion = idInscripcion;
//		this.comision = comision;
//		this.alumno = alumno;
//		this.nota = new Nota();
//		this.aprobada = false;
//	}

//	public Integer getIdInscripcion() {
//		return idInscripcion;
//	}
	
	public Boolean getAprobada() {
		return aprobada;
	}

	public Comision getComision() {
		return comision;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public Nota getNota() {
		return nota;
	}

	//refactorizar clase 11:20
	public Boolean calificar(Integer valorNota) {
		Boolean pudoCalificar = false;
		
		if(valorNota <= 10 && valorNota > 0)
			if(this.nota.getPrimerParcial() == null) {
				this.nota.setPrimerParcial(valorNota);
				pudoCalificar = true;
			}
			else if(this.nota.getSegundoParcial() == null) {
				this.nota.setSegundoParcial(valorNota);
				estaAprobada();
				pudoCalificar = true;
			}
			else if(this.nota.getRecupera() && this.nota.getRecuperatorio() == null){
				this.nota.setRecuperatorio(valorNota);
				estaAprobada();
				pudoCalificar = true;
			}	
		
		return pudoCalificar;
	}
	
	private void estaAprobada() {
		this.aprobada = nota.getPrimerParcial() >= 7 && nota.getSegundoParcial() >= 7;
	}
	
	@Override
	public String toString() {
		return "Comision: " + comision + "\tAlumno: " + alumno;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.alumno.equals(((Inscripcion)obj).getAlumno()) && this.comision.equals(((Inscripcion)obj).getComision());
	}
	
//	@Override
//	public boolean equals(Object obj) {
//		return this.idInscripcion.equals(((Inscripcion)obj).getIdInscripcion());
//	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

}
