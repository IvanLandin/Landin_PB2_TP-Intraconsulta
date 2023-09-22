package ar.unlam.intraconsulta;

import java.util.ArrayList;

public class Materia {

	private Integer idMateria;
	private Integer codigoMateria;
	private String nombreMateria;
	private ArrayList<Materia> listaCorrelativas;

	public Materia(Integer idMateria, Integer codigoMateria, String nombreMateria) {
		this.idMateria = idMateria;
		this.codigoMateria = codigoMateria;
		this.nombreMateria = nombreMateria;
		this.listaCorrelativas = new ArrayList<Materia>();
	}

	public Integer getIdMateria() {
		return idMateria;
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

	public Boolean agregarMateriaCorrelativa(Materia materiaCorrelativa) {
		if (!buscarMateriaCorrelativa(materiaCorrelativa))
			return listaCorrelativas.add(materiaCorrelativa);
		
		return false;
	}
	
	public boolean eliminarMateriaCorrelativa(Materia materiaCorrelativa) {
		if (buscarMateriaCorrelativa(materiaCorrelativa))
			return listaCorrelativas.remove(materiaCorrelativa);
		
		return false;
	}

	private Boolean buscarMateriaCorrelativa(Materia materiaCorrelativa) {
		return listaCorrelativas.contains(materiaCorrelativa);
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
		if (obj instanceof Materia)
			return this.idMateria.equals(((Materia) obj).getIdMateria());
		
		return false;
	}

	@Override
	public int hashCode() {
		return idMateria;
	}
}
