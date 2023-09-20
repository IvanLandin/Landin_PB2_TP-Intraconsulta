package ar.unlam.intraconsulta;

import java.util.ArrayList;

public class Universidad {

	private String nombre;
	private ArrayList<CicloLectivo> listaCiclosLectivos;
	private ArrayList<Materia> listaMaterias;
	private ArrayList<Comision> listaComisiones;
	private ArrayList<Alumno> listaAlumnos;
	private ArrayList<Profesor> listaProfesores;
	private static Integer idMateria;
	private static Integer idCicloLectivo;
	private static Integer idComision;
	private static Integer idAlumno;
	private static Integer idProfesor;

	public Universidad(String nombre) {
		idComision = 0;
		idCicloLectivo = 0;
		idMateria = 0;
		idAlumno = 0;
		idProfesor = 0;
		this.nombre = nombre;
		this.listaCiclosLectivos = new ArrayList<CicloLectivo>();
		this.listaMaterias = new ArrayList<Materia>();
		this.listaComisiones = new ArrayList<Comision>();
		this.listaAlumnos = new ArrayList<Alumno>();
		this.listaProfesores = new ArrayList<Profesor>();
	}
	
	public Integer getIdCicloLectivo() {
		return ++idCicloLectivo;
	}
	
	public Integer getIdMateria() {
		return ++idMateria;
	}
	
	public Integer getIdComision() {
		return ++idComision;
	}
	
	public Integer getIdAlumno() {
		return ++idAlumno;
	}
	
	public Integer getIdProfesor() {
		return ++idProfesor;
	}

	public String getNombre() {
		return nombre;
	}

	public ArrayList<Materia> getListaMaterias() {
		return listaMaterias;
	}

	public ArrayList<CicloLectivo> getListaCiclosLectivos() {
		return listaCiclosLectivos;
	}
	
	public ArrayList<Comision> getListaComisiones() {
		return listaComisiones;
	}
	
	public ArrayList<Alumno> getListaAlumnos() {
		return listaAlumnos;
	}

	public ArrayList<Profesor> getListaProfesores() {
		return listaProfesores;
	}

	public Boolean agregarCicloLectivo(CicloLectivo cicloLectivo) {
		Boolean pudoAgregarCicloLectivo = false;
		
		if(!buscarCicloLectivo(cicloLectivo) && !verificarSuperposicionCicloLectivo(cicloLectivo))
			pudoAgregarCicloLectivo = listaCiclosLectivos.add(cicloLectivo);
		
		return pudoAgregarCicloLectivo;
	}
	
	private Boolean buscarCicloLectivo(CicloLectivo cicloLectivo) {
		return listaCiclosLectivos.contains(cicloLectivo);
	}

	private Boolean verificarSuperposicionCicloLectivo(CicloLectivo nuevoCicloLectivo) {
		for (CicloLectivo cicloLectivo : listaCiclosLectivos) {
			return cicloLectivo.verificarSuperposicionDeIntervalos(nuevoCicloLectivo);
		}

		return false;
	}

//	private CicloLectivo buscarCicloLectivoPorId(Integer idCicloLectivo) {
//		CicloLectivo cicloLectivoBuscado = null;
//
//		for (CicloLectivo cicloLectivo : listaCiclosLectivos) {
//			if (cicloLectivo.getIdCicloLectivo().equals(idCicloLectivo)) {
//				cicloLectivoBuscado = cicloLectivo;
//			}
//		}
//
//		return cicloLectivoBuscado;
//	}

	public Boolean agregarMateria(Materia nuevaMateria) {
		Boolean pudoAgregarMateria = false;
		Materia materiaBuscada = buscarMateriaPorId(nuevaMateria.getIdMateria());

		if (materiaBuscada == null) {
			listaMaterias.add(nuevaMateria);
			pudoAgregarMateria = true;
		}
		
		return pudoAgregarMateria;
	}

	private Materia buscarMateriaPorId(Integer idMateria) {
		Materia materiaBuscada = null;

		for (Materia materia : listaMaterias) {
			if (materia.getIdMateria().equals(idMateria)) {
				materiaBuscada = materia;
			}
		}

		return materiaBuscada;
	}

	public Boolean agregarComision(Comision nuevaComision) {
		Boolean pudoAgregarComision = false;
		Comision comisionBuscada = buscarComisionPorId(nuevaComision.getId());
		
		if(comisionBuscada == null && !(verificarComisionDuplicada(nuevaComision))) {
			listaComisiones.add(nuevaComision);
			pudoAgregarComision = true;
		}
		
		return pudoAgregarComision;
	}
	
	private Comision buscarComisionPorId(Integer idComision) {
		Comision comisionBuscada = null;
		
		for (Comision comision : listaComisiones) {
			if(comision.getId().equals(idComision)) 
				comisionBuscada = comision;
		}
		
		return comisionBuscada;
	}

	private Boolean verificarComisionDuplicada(Comision nuevaComision) {
		Boolean esDuplicada = false;
		
		for (Comision comision : listaComisiones) {
			if(comision.verificarTurnoCicloLectivoYMateriaDuplicado(nuevaComision)) {
				esDuplicada = true;
			}
		}
		
		return esDuplicada;
	}

	public Boolean agregarAlumno(Alumno alumno) {
		Boolean pudoAgregarAlumno = false;
		Alumno alumnoBuscado = buscarAlumnoPorDni(alumno.getDni());
		
		if(alumnoBuscado == null) {
			listaAlumnos.add(alumno);
			pudoAgregarAlumno = true;
		}
		
		return pudoAgregarAlumno;
	}

	private Alumno buscarAlumnoPorDni(Integer dni) {
		Alumno alumnoBuscado = null;
		
		for (Alumno alumno : listaAlumnos) {
			if(alumno.getDni().equals(dni))
				alumnoBuscado = alumno;
		}
		
		return alumnoBuscado;
	}

	public Boolean agregarProfesor(Profesor profesor) {
		Boolean pudoAgregarProfesor = false;
		Profesor profesorBuscado = buscarProfesorPorDni(profesor.getDni());
		
		if(profesorBuscado == null) {
			listaProfesores.add(profesor);
			pudoAgregarProfesor = true;
		}
		
		return pudoAgregarProfesor;
	}

	private Profesor buscarProfesorPorDni(Integer dni) {
		Profesor profesorBuscado = null;
		
		for (Profesor profesor : listaProfesores) {
			if(profesor.getDni().equals(dni))
				profesorBuscado = profesor;
		}
		
		return profesorBuscado;
	}

	

}
