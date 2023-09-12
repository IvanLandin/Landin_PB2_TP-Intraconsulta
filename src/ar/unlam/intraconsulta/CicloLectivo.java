package ar.unlam.intraconsulta;

import java.util.Date;

public class CicloLectivo {

	private Integer anio;
	private Cuatrimestres cuatrimestre;

	public CicloLectivo(Integer anio, Cuatrimestres cuatrimestre, Date fechaInicio) {
		this.anio = anio;
		this.cuatrimestre = cuatrimestre;
	}

	public Integer getAnio() {
		return anio;
	}

	public Cuatrimestres getCuatrimestre() {
		return cuatrimestre;
	}

	@Override
	public String toString() {
		return "Anio: " + anio + ". Cuatrimestre: " + cuatrimestre;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.anio.equals(((CicloLectivo)obj).getAnio()) && this.cuatrimestre.equals(((CicloLectivo)obj).getCuatrimestre());
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
}
