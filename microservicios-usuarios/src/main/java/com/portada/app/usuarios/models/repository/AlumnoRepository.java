package com.portada.app.usuarios.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.portada.app.usuarios.models.entity.Alumno;

public interface AlumnoRepository extends CrudRepository<Alumno, Long> {

}
