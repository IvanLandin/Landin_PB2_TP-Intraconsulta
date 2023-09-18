package ar.unlam.intraconsulta;

import java.util.ArrayList;

public class Comision {

	private Integer id;
	private Integer numeroComision;
	private Materia materia;
	private ArrayList<DiaCurso> diasCursada;
	private Aula aula;
	private CicloLectivo cicloLectivo;

	public Comision(Integer id, Materia materia, Integer numeroComision, CicloLectivo cicloLectivo, DiaCurso dc1) {
		this.id = id;
		this.materia = materia;
		this.numeroComision = numeroComision;
		this.cicloLectivo = cicloLectivo;
		this.aula = null;
		
		this.diasCursada = new ArrayList<DiaCurso>();
		this.diasCursada.add(dc1);
	}
	
	public Comision(Integer id, Materia materia, Integer numeroComision, CicloLectivo cicloLectivo, DiaCurso dc1, DiaCurso dc2) {
		this.id = id;
		this.materia = materia;
		this.numeroComision = numeroComision;
		this.cicloLectivo = cicloLectivo;
		this.aula = null;
		
		this.diasCursada = new ArrayList<DiaCurso>();
		this.diasCursada.add(dc1);
		this.diasCursada.add(dc2);
	}

	public Integer getId() {
		return id;
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
	
	public CicloLectivo getCicloLectivo() {
		return cicloLectivo;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}
	
	public Aula getAula() {
		return aula;
	}

	@Override
	public String toString() {
		return materia.getCodigoMateria() + " - Com: " + numeroComision;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.id.equals(((Comision)obj).getId());
	}
	
	@Override
	public int hashCode() {
		return id;
	}

}
