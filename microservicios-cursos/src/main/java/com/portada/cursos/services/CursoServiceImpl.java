package com.portada.cursos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portada.commons.services.CommonServicesImpl;
import com.portada.cursos.clients.RespuestaFeignClient;
import com.portada.cursos.models.entity.Curso;
import com.portada.cursos.models.repository.CursoRepository;

@Service
public class CursoServiceImpl extends CommonServicesImpl<Curso, CursoRepository> implements CursoService {
	
	@Autowired
	private RespuestaFeignClient feign;
	
	@Override
	@Transactional(readOnly = true)
	public Curso findCursoByAlumnoId(Long id) {
		return repository.findCursoByAlumnoId(id);
	}

	@Override
	public Iterable<Long> obtenerExamenesIdsConRespuestasAlumnos(Long alumnoId) {
		return feign.obtenerExamenesIdsConRespuestasAlumnos(alumnoId);
	}
}
