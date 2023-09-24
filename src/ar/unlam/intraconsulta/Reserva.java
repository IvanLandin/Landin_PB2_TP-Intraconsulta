package ar.unlam.intraconsulta;

public class Reserva {
	
	private DiasSemana dia;
	private Turnos turno;
	private CicloLectivo cicloLectivo;
	
	public Reserva(DiasSemana dia, Turnos turno, CicloLectivo cicloLectivo) {
		this.dia = dia;
		this.turno = turno;
		this.cicloLectivo = cicloLectivo;
	}

	public DiasSemana getDia() {
		return dia;
	}

	public Turnos getTurno() {
		return turno;
	}

	public CicloLectivo getCicloLectivo() {
		return cicloLectivo;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Reserva)
			return dia.equals(((Reserva)obj).getDia()) && turno.equals(((Reserva)obj).getTurno()) && cicloLectivo.equals(((Reserva)obj).getCicloLectivo());
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return dia.ordinal() + (turno.ordinal() * 6) + cicloLectivo.getIdCicloLectivo();
	}
}
