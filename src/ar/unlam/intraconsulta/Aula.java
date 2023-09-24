package ar.unlam.intraconsulta;

import java.util.ArrayList;

public class Aula {

	private Integer numeroAula;
	private Integer capacidad;
	private ArrayList<Reserva> listaReservas;

	public Aula(Integer numeroAula, Integer capacidad) {
		this.numeroAula = numeroAula;
		this.capacidad = capacidad;
		this.listaReservas = new ArrayList<Reserva>();
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public Integer getNumeroAula() {
		return numeroAula;
	}
	
	public Boolean estaDisponible(ArrayList<DiasSemana> dias, Turnos turno, CicloLectivo cicloLectivo) {
		ArrayList<Reserva> aux = new ArrayList<Reserva>();
		for (DiasSemana dia : dias) {
			aux.add(new Reserva(dia, turno, cicloLectivo));
		}
		return !listaReservas.containsAll(aux);
	}
	
	public Boolean ocupar(DiasSemana dia, Turnos turno, CicloLectivo cicloLectivo) {
		return listaReservas.add(new Reserva(dia, turno, cicloLectivo));
	}
	
	@Override
	public String toString() {
		return "Aula " + numeroAula.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Aula)
			return this.numeroAula.equals(((Aula)obj).getNumeroAula());
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return numeroAula;
	}
}
