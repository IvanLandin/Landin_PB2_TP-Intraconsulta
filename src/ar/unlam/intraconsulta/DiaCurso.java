package ar.unlam.intraconsulta;

public class DiaCurso {

	private String dia;
	private Integer hora;

	public DiaCurso(String dia, Integer hora) {
		this.dia = dia;
		this.hora = hora;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public Integer getHora() {
		return hora;
	}

	public void setHora(Integer hora) {
		this.hora = hora;
	}
	
	@Override
	public String toString() {
		return dia + ". " + hora + "/" + (hora + 4) + " HS";
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.dia.equals(((DiaCurso)obj).getDia()) && this.hora.equals(((DiaCurso)obj).getHora());
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
}
