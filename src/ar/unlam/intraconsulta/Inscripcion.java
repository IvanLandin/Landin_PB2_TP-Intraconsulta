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
		
		if((valorNota <= 10 && valorNota > 0) && (listaDeNotas.size() < 3 || tipoNota.equals(TipoDeNota.FINAL)) && buscarNotaDeTipo(tipoNota) == null)
			return listaDeNotas.add(new Nota(valorNota, tipoNota));
		
		return false;
	}
	
	public Boolean estaPromocionada() {
		if(obtenerNotaFinal() != null)
			return (obtenerNotaFinal() >= 7 && verificarQueLasNotasSeanMayorOIgualAUnNumero(7)) 
					|| (buscarNotaDeTipo(TipoDeNota.FINAL) != null && buscarNotaDeTipo(TipoDeNota.FINAL).getValor() >= 4);
			
		return false;
	}
	
	public Boolean estaAprobada() {
		if(obtenerNotaFinal() != null)
			return obtenerNotaFinal() >= 4;
			
		return false;
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
		Double acumuladorNotas = 0.0;
		
		if(buscarNotaDeTipo(TipoDeNota.FINAL) != null && verificarQueLasNotasSeanMayorOIgualAUnNumero(4)) {
			return buscarNotaDeTipo(TipoDeNota.FINAL).getValor() * 1.0;
		}
		else if(verificarQueLasNotasSeanMayorOIgualAUnNumero(4)) {
			
			switch (listaDeNotas.size()) {
				case 2:
					for (Nota nota : listaDeNotas) {
						acumuladorNotas += nota.getValor();
					}
					break;
				case 3:
					acumuladorNotas += buscarNotaDeTipo(recuperatorioRendido()).getValor();
					acumuladorNotas += buscarNotaDeTipo(parcialValidoEnCasoDeHaberRendidoRecuperatorio()).getValor();
					break;
			}
			
			return acumuladorNotas / 2;
		}
		
		return null;
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
