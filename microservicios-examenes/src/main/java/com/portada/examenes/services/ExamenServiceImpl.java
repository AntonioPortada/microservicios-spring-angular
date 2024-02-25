package com.portada.examenes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portada.commons.examenes.models.entity.Asignatura;
import com.portada.commons.examenes.models.entity.Examen;
import com.portada.commons.services.CommonServicesImpl;
import com.portada.examenes.models.repository.AsignaturaRepository;
import com.portada.examenes.models.repository.ExamenRepository;

@Service
public class ExamenServiceImpl extends CommonServicesImpl<Examen, ExamenRepository> implements ExamenService {

	@Autowired
	private AsignaturaRepository asignaturaRepository; 
	
	@Override
	@Transactional(readOnly = true)
	public List<Examen> findByNombre(String term) {
		return repository.findByNombre(term);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Asignatura> findAllAsignaturas() {
		return asignaturaRepository.findAll();
	}
}
