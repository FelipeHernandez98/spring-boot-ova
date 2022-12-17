package com.ova.java.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ova.java.app.entitys.Calificacion;
import com.ova.java.app.entitys.Tema;
import com.ova.java.app.entitys.Usuario;


@Repository
public interface ICalificacionRepository extends CrudRepository<Calificacion, Long> {

	@Query("select c from Calificacion c where c.idUsuario =?1 and c.idTema =?2")
	public Calificacion getCalificacionByIds(Usuario usuario, Tema tema);
}
