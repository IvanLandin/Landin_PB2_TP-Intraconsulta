package ar.unlam.intraconsulta;

public class Nota {

	private Integer primerParcial;
	private Integer segundoParcial;
	private Integer recuperatorio;
	
	public Integer getPrimerParcial() {
		return primerParcial;
	}
	
	public void setPrimerParcial(Integer primerParcial) {
		this.primerParcial = primerParcial;
	}
	
	public Integer getSegundoParcial() {
		return segundoParcial;
	}
	
	public void setSegundoParcial(Integer segundoParcial) {
		this.segundoParcial = segundoParcial;
	}
	
	public Object getRecuperatorio() {
		return recuperatorio;
	}
	
	public void setRecuperatorio(Integer recuperatorio) {
		this.recuperatorio = recuperatorio;
		
		if(primerParcial < 7) {
			primerParcial = recuperatorio;
		}else {
			segundoParcial = recuperatorio;
		}
	}
	
	public Boolean getRecupera() {
		return primerParcial < 7 ^ segundoParcial < 7;
	}
	
	public Double calcularPromedio() {
		return (primerParcial + segundoParcial) / 2.0;
	}

	
}
