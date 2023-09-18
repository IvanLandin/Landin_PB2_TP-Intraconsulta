package ar.unlam.intraconsulta;

public class Aula {

	private Integer numeroAula;
	private Integer capacidad;

	public Aula(Integer numeroAula, Integer capacidad) {
		this.numeroAula = numeroAula;
		this.capacidad = capacidad;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public Integer getNumeroAula() {
		return numeroAula;
	}
	
	@Override
	public String toString() {
		return numeroAula.toString();
	}

	@Override
	public boolean equals(Object obj) {
		return this.numeroAula.equals(((Aula)obj).getNumeroAula());
	}
	
	@Override
	public int hashCode() {
		return numeroAula;
	}
}
