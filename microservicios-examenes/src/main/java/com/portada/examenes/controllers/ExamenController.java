package com.portada.examenes.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.portada.commons.controllers.CommonController;
import com.portada.commons.examenes.models.entity.Examen;
import com.portada.commons.examenes.models.entity.Pregunta;
import com.portada.examenes.services.ExamenService;

@RestController
public class ExamenController extends CommonController<Examen, ExamenService> {

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@RequestBody Examen examen, @PathVariable Long id) {
		
		Optional<Examen> o = service.findById(id);
		
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Examen examenDb = o.get();
		examenDb.setNombre(examen.getNombre());
		
		List<Pregunta> eliminadas = new ArrayList<>();
		
		examenDb.getPreguntas().forEach(pdb -> {
			if(!examen.getPreguntas().contains(pdb)) {
				eliminadas.add(pdb);
			}
		});
		
		eliminadas.forEach(p -> {
			examenDb.removePregunta(p);
		});
		
		examenDb.setPreguntas(examen.getPreguntas());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenDb));
	}
	
	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrar(@PathVariable String term) {
		return ResponseEntity.ok(service.findByNombre(term));
	}
	
	@GetMapping("/asignaturas")
	public ResponseEntity<?> listarAsignaturas() {
		return ResponseEntity.ok(service.findAllAsignaturas());
	}
}
