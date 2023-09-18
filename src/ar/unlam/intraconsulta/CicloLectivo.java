package ar.unlam.intraconsulta;

import java.time.LocalDate;

public class CicloLectivo {

	private Integer idCicloLectivo;
	private LocalDate fechaInicio;
	private LocalDate fechaFinalizacion;
	private LocalDate fechaInicioInscripcion;
	private LocalDate fechaFinalizacionInscripcion;

	public CicloLectivo(Integer idCicloLectivo, LocalDate fechaInicio, LocalDate fechaFinalizacion, LocalDate fechaInicioInscripcion, LocalDate fechaFinalizacionInscripcion) {
		this.idCicloLectivo = idCicloLectivo;
		this.fechaInicio = fechaInicio;
		this.fechaFinalizacion = fechaFinalizacion;
		this.fechaInicioInscripcion = fechaInicioInscripcion;
		this.fechaFinalizacionInscripcion = fechaFinalizacionInscripcion;
	}
	
	public Integer getIdCicloLectivo() {
		return idCicloLectivo;
	}
	
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public LocalDate getFechaFinalizacion() {
		return fechaFinalizacion;
	}

	public LocalDate getFechaInicioInscripcion() {
		return fechaInicioInscripcion;
	}

	public LocalDate getFechaFinalizacionInscripcion() {
		return fechaFinalizacionInscripcion;
	}

	@Override
	public String toString() {
		return "Inicio del ciclo lectivo: " + fechaInicio + ". Finalizacion del ciclo lectivo: " + fechaFinalizacion;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.idCicloLectivo.equals(((CicloLectivo)obj).getIdCicloLectivo());
	}
	
	@Override
	public int hashCode() {
		return idCicloLectivo;
	}
}
