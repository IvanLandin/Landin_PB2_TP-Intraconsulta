package ar.unlam.intraconsulta;

import java.util.ArrayList;

public class Universidad {

	private String nombre;
	private ArrayList<CicloLectivo> listaCiclosLectivos;
	private ArrayList<Materia> listaMaterias;
	private ArrayList<Comision> listaComisiones;
	private ArrayList<Alumno> listaAlumnos;
	private ArrayList<Profesor> listaProfesores;
	private ArrayList<Inscripcion> listaInscripciones;
	private ArrayList<ProfesorComision> listaAsignacionesProfesorComision;
	private static Integer idMateria;
	private static Integer idCicloLectivo;
	private static Integer idComision;
	private static Integer idAlumno;
	private static Integer idProfesor;
	private static Integer idInscripcion;
	private static Integer idProfesorComision;

	public Universidad(String nombre) {
		idComision = 0;
		idCicloLectivo = 0;
		idMateria = 0;
		idAlumno = 0;
		idProfesor = 0;
		idInscripcion = 0;
		idProfesorComision = 0;
		this.nombre = nombre;
		this.listaCiclosLectivos = new ArrayList<CicloLectivo>();
		this.listaMaterias = new ArrayList<Materia>();
		this.listaComisiones = new ArrayList<Comision>();
		this.listaAlumnos = new ArrayList<Alumno>();
		this.listaProfesores = new ArrayList<Profesor>();
		this.listaInscripciones = new ArrayList<Inscripcion>();
		this.listaAsignacionesProfesorComision = new ArrayList<ProfesorComision>();
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

	public Integer getIdAsignacionProfesorComision() {
		return ++idProfesorComision;
	}

	public Integer getIdInscripcion() {
		return ++idInscripcion;
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

	public ArrayList<Inscripcion> getListaInscripciones() {
		return listaInscripciones;
	}

	public ArrayList<Profesor> getListaProfesores() {
		return listaProfesores;
	}

	public ArrayList<ProfesorComision> getListaAsignacionesProfesorComision() {
		return listaAsignacionesProfesorComision;
	}

	/*--------------------------------------------------CICLO LECTIVO--------------------------------------------------*/

	public Boolean agregarCicloLectivo(CicloLectivo cicloLectivo) {
		Boolean pudoAgregarCicloLectivo = false;

		if (!buscarCicloLectivo(cicloLectivo) && !verificarSuperposicionCicloLectivo(cicloLectivo))
			pudoAgregarCicloLectivo = listaCiclosLectivos.add(cicloLectivo);

		return pudoAgregarCicloLectivo;
	}

	private Boolean buscarCicloLectivo(CicloLectivo cicloLectivo) {
		return listaCiclosLectivos.contains(cicloLectivo);
	}

	private Boolean verificarSuperposicionCicloLectivo(CicloLectivo nuevoCicloLectivo) {
		for (CicloLectivo cicloLectivo : listaCiclosLectivos) {
			if (cicloLectivo.verificarSuperposicionDeIntervalos(nuevoCicloLectivo))
				return true;
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

	/*--------------------------------------------------MATERIA--------------------------------------------------*/

	public Boolean agregarMateria(Materia nuevaMateria) {
		Boolean pudoAgregarMateria = false;

		if (!buscarMateria(nuevaMateria))
			pudoAgregarMateria = listaMaterias.add(nuevaMateria);

		return pudoAgregarMateria;
	}

	private Boolean buscarMateria(Materia materiaBuscada) {
		return listaMaterias.contains(materiaBuscada);
	}

	public Boolean asignarMateriaCorrelativa(Integer idMateria, Integer idCorrelativa) {
		Materia materiaBuscada = buscarMateriaPorId(idMateria);
		Materia correlativaBuscada = buscarMateriaPorId(idCorrelativa);

		if (materiaBuscada != null && correlativaBuscada != null)
			return materiaBuscada.agregarMateriaCorrelativa(correlativaBuscada);

		return false;
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

	/*--------------------------------------------------COMISION--------------------------------------------------*/

	public Boolean agregarComision(Comision nuevaComision) {
		Boolean pudoAgregarComision = false;
		Comision comisionBuscada = buscarComisionPorId(nuevaComision.getId());

		if (comisionBuscada == null && !(verificarComisionDuplicada(nuevaComision))) {
			listaComisiones.add(nuevaComision);
			pudoAgregarComision = true;
		}

		return pudoAgregarComision;
	}

	private Comision buscarComisionPorId(Integer idComision) {
		Comision comisionBuscada = null;

		for (Comision comision : listaComisiones) {
			if (comision.getId().equals(idComision))
				comisionBuscada = comision;
		}

		return comisionBuscada;
	}

	private Boolean verificarComisionDuplicada(Comision nuevaComision) {
		Boolean esDuplicada = false;

		for (Comision comision : listaComisiones) {
			if (comision.verificarTurnoCicloLectivoYMateriaDuplicado(nuevaComision)) {
				esDuplicada = true;
			}
		}

		return esDuplicada;
	}

	/*--------------------------------------------------ALUMNO--------------------------------------------------*/

	public Boolean agregarAlumno(Alumno alumno) {
		Boolean pudoAgregarAlumno = false;
		Alumno alumnoBuscado = buscarAlumnoPorDni(alumno.getDni());

		if (alumnoBuscado == null) {
			listaAlumnos.add(alumno);
			pudoAgregarAlumno = true;
		}

		return pudoAgregarAlumno;
	}

	private Alumno buscarAlumnoPorDni(Integer dni) {
		Alumno alumnoBuscado = null;

		for (Alumno alumno : listaAlumnos) {
			if (alumno.getDni().equals(dni))
				alumnoBuscado = alumno;
		}

		return alumnoBuscado;
	}

	/*--------------------------------------------------PROFESOR--------------------------------------------------*/

	public Boolean agregarProfesor(Profesor profesor) {
		Boolean pudoAgregarProfesor = false;
		Profesor profesorBuscado = buscarProfesorPorDni(profesor.getDni());

		if (profesorBuscado == null) {
			listaProfesores.add(profesor);
			pudoAgregarProfesor = true;
		}

		return pudoAgregarProfesor;
	}

	private Profesor buscarProfesorPorDni(Integer dni) {
		Profesor profesorBuscado = null;

		for (Profesor profesor : listaProfesores) {
			if (profesor.getDni().equals(dni))
				profesorBuscado = profesor;
		}

		return profesorBuscado;
	}

	/*--------------------------------------------------INSCRIPCION--------------------------------------------------*/

	public Boolean inscribirAlumnoAComision(Integer dniAlumno, Integer idComision) {
		Alumno alumnoBuscado = buscarAlumnoPorDni(dniAlumno);
		Comision comisionBuscada = buscarComisionPorId(idComision);
		Inscripcion inscripcionBuscada = buscarInscripcion(dniAlumno, idComision);
		
		if(alumnoBuscado != null && comisionBuscada != null && inscripcionBuscada == null)
			return listaInscripciones.add(new Inscripcion(getIdInscripcion(), comisionBuscada, alumnoBuscado));
		
		return false;
	}
	
	private Inscripcion buscarInscripcion(Integer dniAlumno, Integer idComision) {
		for (Inscripcion inscripcion : listaInscripciones) {
			if(inscripcion.getAlumno().getDni().equals(dniAlumno) && inscripcion.getComision().getId().equals(idComision))
				return inscripcion;
		}
		
		return null;
	}

	public ArrayList<Inscripcion> obtenerListaDeAlumnosDeUnaComision(Comision comision) {
		ArrayList<Inscripcion> listaInscriptos = new ArrayList<Inscripcion>();

		for (Inscripcion inscripcion : listaInscripciones) {
			if (inscripcion.getComision().equals(comision))
				listaInscriptos.add(inscripcion);
		}

		return listaInscriptos;
	}

	private Integer calcularDocentesNecesarios(Comision comision) {
		return (int) Math.ceil((double)obtenerListaDeAlumnosDeUnaComision(comision).size() / 20);
	}

	/*--------------------------------------------------PROFECOMISION--------------------------------------------------*/

	public Boolean asignarProfesorAComision(Integer idComision, Integer dniProfesor) {
		Profesor profesorBuscado = buscarProfesorPorDni(dniProfesor);
		Comision comisionBuscada = buscarComisionPorId(idComision);
		ProfesorComision asignacionProfesorComisionBuscada = buscarProfesorComision(comisionBuscada, profesorBuscado);

		if ((profesorBuscado != null && comisionBuscada != null) && asignacionProfesorComisionBuscada == null
				&& calcularDocentesNecesarios(comisionBuscada) > obtenerListaDeProfesoresDeUnaComision(comisionBuscada).size()) {
			
			ProfesorComision nuevaAsignacion = new ProfesorComision(getIdAsignacionProfesorComision(), profesorBuscado, comisionBuscada);
			return listaAsignacionesProfesorComision.add(nuevaAsignacion);
		}

		return false;
	}

	public ArrayList<ProfesorComision> obtenerListaDeProfesoresDeUnaComision(Comision comision) {
		ArrayList<ProfesorComision> listaProfesores = new ArrayList<ProfesorComision>();

		for (ProfesorComision asignacionProfeComision : listaAsignacionesProfesorComision) {
			if (asignacionProfeComision.getComision().equals(comision))
				listaProfesores.add(asignacionProfeComision);
		}

		return listaProfesores;
	}

	private ProfesorComision buscarProfesorComision(Comision comisionBuscada, Profesor profesorBuscado) {
		ProfesorComision asignacionBuscada = null;

		for (ProfesorComision profesorComision : listaAsignacionesProfesorComision) {
			if (profesorComision.getComision().equals(comisionBuscada)
					&& profesorComision.getProfesor().equals(profesorBuscado))
				asignacionBuscada = profesorComision;
		}

		return asignacionBuscada;
	}

	

	/*--------------------------------------------------AULA--------------------------------------------------*/

	/*--------------------------------------------------NOTA--------------------------------------------------*/
}
