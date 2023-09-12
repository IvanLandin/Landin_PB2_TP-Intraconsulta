package ar.unlam.intraconsulta;

import java.util.ArrayList;

public class Materia {

	private Integer codigoMateria;
	private String nombreMateria;
	private ArrayList<Integer> listaCorrelativas;
	private ArrayList<Comision> listaComisiones;

	public Materia(Integer codigoMateria, String nombreMateria, ArrayList<Integer> listaCorrelativas) {
		this.codigoMateria = codigoMateria;
		this.nombreMateria = nombreMateria;
		this.listaCorrelativas = listaCorrelativas;
		this.listaComisiones = new ArrayList<Comision>();
	}

	public Materia(Integer codigoMateria, String nombreMateria) {
		this.codigoMateria = codigoMateria;
		this.nombreMateria = nombreMateria;
		this.listaCorrelativas = new ArrayList<Integer>();
		this.listaComisiones = new ArrayList<Comision>();
	}

	public String getNombreMateria() {
		return nombreMateria;
	}

	public void setNombreMateria(String nombreMateria) {
		this.nombreMateria = nombreMateria;
	}

	public ArrayList<Integer> getListaCorrelativas() {
		return listaCorrelativas;
	}

	public void setListaCorrelativas(ArrayList<Integer> listaCorrelativas) {
		this.listaCorrelativas = listaCorrelativas;
	}

	public Integer getCodigoMateria() {
		return codigoMateria;
	}
	
	public ArrayList<Comision> getListaComisiones() {
		return listaComisiones;
	}
	
	public Boolean agregarComision(Comision nuevaComision) {
		Boolean pudoAgregarComision = false;
		
		if(buscarComisionPorNumero(nuevaComision.getNumeroComision()) == null) {
			listaComisiones.add(nuevaComision);
			pudoAgregarComision = true;
		}
		
		return pudoAgregarComision;
	}
	
	public Comision buscarComisionPorNumero(Integer numeroComisionBuscada) {
		Comision comisionBuscada = null;
		
		for (int i = 0; i < this.listaComisiones.size(); i++) {
			if(this.listaComisiones.get(i).getNumeroComision().equals(numeroComisionBuscada)) {
				comisionBuscada = this.listaComisiones.get(i);
				break;
			}
		}
		
		return comisionBuscada;
	}

	@Override
	public String toString() {
		return codigoMateria + " - " + nombreMateria;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.codigoMateria.equals(((Materia)obj).getCodigoMateria());
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
