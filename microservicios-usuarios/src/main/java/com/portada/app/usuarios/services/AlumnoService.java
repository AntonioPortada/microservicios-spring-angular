package com.portada.app.usuarios.services;

import java.util.List;

import com.portada.commons.alumnos.models.entity.Alumno;
import com.portada.commons.services.CommonService;

public interface AlumnoService extends CommonService<Alumno> {
	
	public List<Alumno> findByNombreOrApellido(String term);
}
