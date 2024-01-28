package com.portada.app.usuarios.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.portada.app.usuarios.services.AlumnoService;
import com.portada.commons.alumnos.models.entity.Alumno;
import com.portada.commons.controllers.CommonController;

@RestController
public class AlumnoController extends CommonController<Alumno, AlumnoService> {
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@RequestBody Alumno alumno, @PathVariable Long id) {
		Optional<Alumno> o = service.findById(id);
		
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Alumno alumnoEntity = o.get();
		alumnoEntity.setNombre(alumno.getNombre());
		alumnoEntity.setApellido(alumno.getApellido());
		alumnoEntity.setEmail(alumno.getEmail());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoEntity));
	}
	
	@GetMapping("/filtar/{term}")
	public ResponseEntity<?> filtar(@PathVariable String term) {
		return ResponseEntity.ok(this.service.findByNombreOrApellido(term));
	}
}
