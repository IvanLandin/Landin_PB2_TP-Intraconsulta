package ar.unlam.intraconsulta;

import java.util.ArrayList;

public class Materia {

	private Integer codigoMateria;
	private String nombreMateria;
	private ArrayList<Materia> listaCorrelativas;

	public Materia(Integer codigoMateria, String nombreMateria, ArrayList<Materia> listaCorrelativas) {
		this.codigoMateria = codigoMateria;
		this.nombreMateria = nombreMateria;
		this.listaCorrelativas = listaCorrelativas;
	}

	public Materia(Integer codigoMateria, String nombreMateria) {
		this.codigoMateria = codigoMateria;
		this.nombreMateria = nombreMateria;
		this.listaCorrelativas = new ArrayList<Materia>();
	}

	public String getNombreMateria() {
		return nombreMateria;
	}

	public void setNombreMateria(String nombreMateria) {
		this.nombreMateria = nombreMateria;
	}

	public ArrayList<Materia> getListaCorrelativas() {
		return listaCorrelativas;
	}

	public void setListaCorrelativas(ArrayList<Materia> listaCorrelativas) {
		this.listaCorrelativas = listaCorrelativas;
	}

	public Integer getCodigoMateria() {
		return codigoMateria;
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
