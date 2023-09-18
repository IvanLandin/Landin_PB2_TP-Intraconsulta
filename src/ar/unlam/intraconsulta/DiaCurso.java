package ar.unlam.intraconsulta;

public class DiaCurso {

	private DiasSemana dia;
	private Turnos turno;
	
	public DiaCurso(DiasSemana dia, Turnos turno) {
		this.dia = dia;
		this.turno = turno;
	}
	
	public DiasSemana getDia() {
		return dia;
	}

	public Turnos getTurno() {
		return turno;
	}

	@Override
	public String toString() {
		return dia.toString() + ", TURNO " + turno.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.dia.equals(((DiaCurso)obj).getDia()) && this.turno.equals(((DiaCurso)obj).getTurno());
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
}
