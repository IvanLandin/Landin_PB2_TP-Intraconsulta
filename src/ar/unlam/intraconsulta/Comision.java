package ar.unlam.intraconsulta;

import java.util.ArrayList;

public class Comision {

	private Integer codigoMateria;
	private Integer numeroComision;
	private ArrayList<DiaCurso> diasCursada;
	private Aula aula;

	public Comision(Integer codigoMateria, Integer numeroComision) {
		this.codigoMateria = codigoMateria;
		this.numeroComision = numeroComision;
		this.diasCursada = new ArrayList<DiaCurso>();
		this.aula = null;
	}

	public void setDiaCursada(DiaCurso diaCursada) {
		diasCursada.add(diaCursada);
	}

	public Integer getCodigoMateria() {
		return codigoMateria;
	}

	public Integer getNumeroComision() {
		return numeroComision;
	}

	public ArrayList<DiaCurso> getDiasCursada() {
		return diasCursada;
	}
	
	public void setAula(Aula aula) {
		this.aula = aula;
	}
	
	public Aula getAula() {
		return aula;
	}

	@Override
	public String toString() {
		return codigoMateria + " - Com: " + numeroComision;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.codigoMateria.equals(((Comision)obj).getCodigoMateria()) && this.numeroComision.equals(((Comision)obj).getNumeroComision());
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	

}
