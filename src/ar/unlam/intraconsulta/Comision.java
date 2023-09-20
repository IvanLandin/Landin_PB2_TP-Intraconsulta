package ar.unlam.intraconsulta;

import java.util.ArrayList;

public class Comision {

	private Integer id;
	private Integer numeroComision;
	private Materia materia;
	private Turnos turno;
	private ArrayList<DiasSemana> listaDiasDeCurso;
	private Aula aula;
	private CicloLectivo cicloLectivo;

	public Comision(Integer id, Materia materia, Integer numeroComision, CicloLectivo cicloLectivo, Turnos turno) {
		this.id = id;
		this.materia = materia;
		this.numeroComision = numeroComision;
		this.cicloLectivo = cicloLectivo;
		this.turno = turno;
		this.listaDiasDeCurso = new ArrayList<DiasSemana>();
		this.aula = null;
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

	public ArrayList<DiasSemana> getListaDiasDeCurso() {
		return listaDiasDeCurso;
	}

	public CicloLectivo getCicloLectivo() {
		return cicloLectivo;
	}

	public Turnos getTurno() {
		return turno;
	}

	public Boolean asignarAula(Aula aula) {
		if(this.aula == null){
			this.aula = aula;
			return true;
		}
		return false;
	}

	public Aula getAula() {
		return aula;
	}
	
	public Boolean asignarDiaDeCurso(DiasSemana diaDeCurso) {
		if(!listaDiasDeCurso.contains(diaDeCurso) && listaDiasDeCurso.size() < 2)
			return listaDiasDeCurso.add(diaDeCurso);
		
		return false;
	}

	public Boolean verificarTurnoCicloLectivoYMateriaDuplicado(Comision nuevaComision) {

		return cicloLectivo.equals(nuevaComision.getCicloLectivo())
				&& materia.equals(nuevaComision.getMateriaComision()) && turno.equals(nuevaComision.getTurno());
	}

	@Override
	public String toString() {
		return materia.getCodigoMateria() + " - Com: " + numeroComision;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Comision)
			return this.id.equals(((Comision) obj).getId());
		
		return false;
	}

	@Override
	public int hashCode() {
		return id;
	}

}
