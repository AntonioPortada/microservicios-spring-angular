package com.portada.cursos.services;

import com.portada.commons.services.CommonService;
import com.portada.cursos.models.entity.Curso;

public interface CursoService extends CommonService<Curso> {
	
	public Curso findCursoByAlumnoId(Long id);
}
