package ar.unlam.intraconsulta;

import java.util.ArrayList;

public class Inscripcion {
	
	private Integer idInscripcion;
	private Comision comision;
	private Alumno alumno;
	private ArrayList<Nota> listaDeNotas;
	
	public Inscripcion(Integer idInscripcion, Comision comision, Alumno alumno) {
		this.idInscripcion = idInscripcion;
		this.comision = comision;
		this.alumno = alumno;
		this.listaDeNotas = new ArrayList<Nota>();
	}

	public Integer getIdInscripcion() {
		return idInscripcion;
	}

	public Comision getComision() {
		return comision;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public ArrayList<Nota> getListaDeNotas() {
		return listaDeNotas;
	}
	
	public Boolean calificar(Integer valorNota, TipoDeNota tipoNota) {
		
		if((valorNota <= 10 && valorNota > 0) && listaDeNotas.size() < 3 && buscarNotaDeTipo(tipoNota) == null)
			return listaDeNotas.add(new Nota(valorNota, tipoNota));
		
		return false;
	}
	
	public Boolean estaPromocionada() {
		return obtenerNotaFinal() >= 7 && verificarQueLasNotasSeanMayorOIgualAUnNumero(7);
	}
	
	public Boolean estaAprobada() {
		return obtenerNotaFinal() >= 4;
	}

	private Boolean verificarQueLasNotasSeanMayorOIgualAUnNumero(Integer numero) {
		for (Nota nota : listaDeNotas) {
			if(parcialValidoEnCasoDeHaberRendidoRecuperatorio() != null) {
				if((nota.getTipo().equals(parcialValidoEnCasoDeHaberRendidoRecuperatorio()) || nota.getTipo().equals(recuperatorioRendido())) && 
					nota.getValor() < numero) {
					return false;
				}
			}
			else {
				if(nota.getValor() < numero)
					return false;
			}
		}
		
		return true;
	}
	
	public Double obtenerNotaFinal() {
		Double suma = 0.0;
		
		if(!verificarQueLasNotasSeanMayorOIgualAUnNumero(4))
			return 2.0;
		
		if(listaDeNotas.size() == 2) {
			for (Nota nota : listaDeNotas) {
				suma += nota.getValor();
			}
		}
		else if(listaDeNotas.size() == 3){
			if(recuperatorioRendido() != null){
				if(recuperatorioRendido().equals(TipoDeNota.RECUPERATORIO_PRIMER_PARCIAL)) {
					suma += buscarNotaDeTipo(TipoDeNota.SEGUNDO_PARCIAL).getValor();
					suma += buscarNotaDeTipo(TipoDeNota.RECUPERATORIO_PRIMER_PARCIAL).getValor();
				}
				else {
					suma += buscarNotaDeTipo(TipoDeNota.PRIMER_PARCIAL).getValor();
					suma += buscarNotaDeTipo(TipoDeNota.RECUPERATORIO_SEGUNDO_PARCIAL).getValor();
				}
			}
		}
		
		return suma / 2;
	}
	
	private TipoDeNota parcialValidoEnCasoDeHaberRendidoRecuperatorio() {
		if(recuperatorioRendido() != null) {
			if(recuperatorioRendido().equals(TipoDeNota.RECUPERATORIO_SEGUNDO_PARCIAL))
				return TipoDeNota.PRIMER_PARCIAL;
			else
				return TipoDeNota.SEGUNDO_PARCIAL;			
		}
		
		return null;
	}
	
	private TipoDeNota recuperatorioRendido() {
		for (Nota nota : listaDeNotas) {
			if(nota.getTipo().equals(TipoDeNota.RECUPERATORIO_SEGUNDO_PARCIAL) || nota.getTipo().equals(TipoDeNota.RECUPERATORIO_PRIMER_PARCIAL))
				return nota.getTipo();
		}
		return null;
	}
	
	private Nota buscarNotaDeTipo(TipoDeNota tipo) {
		for (Nota nota : listaDeNotas) {
			if(nota.getTipo().equals(tipo))
				return nota;
		}
		return null;
	}
	
	@Override
	public String toString() {
		return "Comision: " + comision + "\tAlumno: " + alumno;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Inscripcion)
			return idInscripcion.equals(((Inscripcion)obj).getIdInscripcion());
			
		return false;
	}
	
	@Override
	public int hashCode() {
		return idInscripcion;
	}

}
