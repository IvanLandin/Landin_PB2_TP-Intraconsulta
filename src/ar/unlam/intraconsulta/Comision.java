package ar.unlam.intraconsulta;

import java.util.ArrayList;

public class Comision {

	private Materia materia;
	private Integer numeroComision;
	private ArrayList<DiaCurso> diasCursada;
	private Aula aula;
	//agregar CicloLectivo
	//agregar arraylist inscripcion
	//agregar arraylist profecomision(profe y curso)

	public Comision(Materia materia, Integer numeroComision) {
		this.materia = materia;
		this.numeroComision = numeroComision;
		this.diasCursada = new ArrayList<DiaCurso>();
		this.aula = null;
	}

	public void setDiaCursada(DiaCurso diaCursada) {
		diasCursada.add(diaCursada);
	}

	public Materia getMateriaComision() {
		return materia;
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
		return materia + " - Com: " + numeroComision;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.materia.equals(((Comision)obj).getMateriaComision()) && this.numeroComision.equals(((Comision)obj).getNumeroComision());
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	

}
