package com.portada.examenes.services;

import java.util.List;

import com.portada.commons.examenes.models.entity.Asignatura;
import com.portada.commons.examenes.models.entity.Examen;
import com.portada.commons.services.CommonService;

public interface ExamenService extends CommonService<Examen> {

	public List<Examen> findByNombre(String term);
	
	public Iterable<Asignatura> findAllAsignaturas();
}
