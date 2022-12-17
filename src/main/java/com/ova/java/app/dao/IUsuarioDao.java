package com.ova.java.app.dao;

import java.util.List;

import com.ova.java.app.entitys.Usuario;


public interface IUsuarioDao {

	List<Usuario> getUsuarios();
	
	Usuario getUsuario(Long id);
	
	void eliminar(Long id);
	
	boolean registrar(Usuario usuario);
	
	Usuario userByCredentials(Usuario usuario);
	
	boolean verificarUsuario(Usuario usuario);
	
	
}
