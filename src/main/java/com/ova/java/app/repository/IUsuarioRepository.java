package com.ova.java.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ova.java.app.entitys.Usuario;


@Repository
public interface IUsuarioRepository extends CrudRepository<Usuario, Long> {

}
