package com.portada.app.usuarios.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portada.app.usuarios.models.repository.AlumnoRepository;
import com.portada.commons.alumnos.models.entity.Alumno;
import com.portada.commons.services.CommonServicesImpl;

@Service
public class AlumnoServicesImpl extends CommonServicesImpl<Alumno, AlumnoRepository> implements AlumnoService {
	
	@Override
	@Transactional(readOnly = true)
	public List<Alumno> findByNombreOrApellido(String term) {
		return repository.findByNombreOrApellido(term);
	}
}
