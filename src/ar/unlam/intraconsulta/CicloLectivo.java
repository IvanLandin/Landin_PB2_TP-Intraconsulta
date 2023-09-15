package ar.unlam.intraconsulta;

import java.util.ArrayList;
import java.util.Date;

public class CicloLectivo {

	//usar LocalDate, Date, Calendar o GregorianCalendar
	private Integer anio;
	private Cuatrimestres cuatrimestre;
	private ArrayList<Comision> listaComisiones;

	public CicloLectivo(Integer anio, Cuatrimestres cuatrimestre, Date fechaInicio) {
		this.anio = anio;
		this.cuatrimestre = cuatrimestre;
		this.listaComisiones = new ArrayList<Comision>();
	}

	public Integer getAnio() {
		return anio;
	}

	public Cuatrimestres getCuatrimestre() {
		return cuatrimestre;
	}
	
	public Comision buscarComisionEnLista(Comision comisionBuscada) {
		Comision comisionEncontrada = null;
		
		for (Comision comision : listaComisiones) {
			if(comision.equals(comisionBuscada)) {
				comisionEncontrada = comision;
				break;
			}
		}
		
		return comisionEncontrada;
	}
	
	public void agregarComision(Comision comision) {
		Comision comisionBuscada = buscarComisionEnLista(comision);
		
		if(comisionBuscada == null) {
			listaComisiones.add(comision);
		}
	}

	public ArrayList<Comision> getListaComisiones() {
		return listaComisiones;
	}

	@Override
	public String toString() {
		return "Anio: " + anio + ". Cuatrimestre: " + cuatrimestre;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.anio.equals(((CicloLectivo)obj).getAnio()) && this.cuatrimestre.equals(((CicloLectivo)obj).getCuatrimestre());
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
}
