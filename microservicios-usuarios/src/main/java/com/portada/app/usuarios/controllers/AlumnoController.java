package com.portada.app.usuarios.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.portada.app.usuarios.services.AlumnoService;
import com.portada.commons.alumnos.models.entity.Alumno;
import com.portada.commons.controllers.CommonController;

import jakarta.validation.Valid;

@RestController
public class AlumnoController extends CommonController<Alumno, AlumnoService> {
	
	@GetMapping("/upload/img/{id}")
	public ResponseEntity<?> verFoto(@PathVariable Long id) {
		
		Optional<Alumno> o = service.findById(id);
		
		if(o.isEmpty() || o.get().getFoto() == null) {
			return ResponseEntity.notFound().build();
		}
		
		Resource imagen = new ByteArrayResource(o.get().getFoto());
		
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Alumno alumno, BindingResult result, @PathVariable Long id) {
		
		if(result.hasErrors()) {
			return validar(result);
		}
		
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

	@PostMapping("crear-con-foto")
	public ResponseEntity<?> crearConFoto(@Valid Alumno alumno, BindingResult result, @RequestParam MultipartFile archivo) throws IOException {
		
		if(!archivo.isEmpty()) {
			alumno.setFoto(archivo.getBytes());
		}
		
		return super.crear(alumno, result);
	}
	
	@PutMapping("/editar-con-foto/{id}")
	public ResponseEntity<?> editarConFoto(@Valid Alumno alumno, BindingResult result, @PathVariable Long id, @RequestParam MultipartFile archivo) throws IOException {
		
		if(result.hasErrors()) {
			return validar(result);
		}
		
		Optional<Alumno> o = service.findById(id);
		
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Alumno alumnoEntity = o.get();
		alumnoEntity.setNombre(alumno.getNombre());
		alumnoEntity.setApellido(alumno.getApellido());
		alumnoEntity.setEmail(alumno.getEmail());
		
		if(!archivo.isEmpty()) {
			alumno.setFoto(archivo.getBytes());
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoEntity));
	}
}
