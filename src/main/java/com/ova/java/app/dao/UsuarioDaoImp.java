package com.ova.java.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ova.java.app.entitys.Usuario;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
 
@Repository
public class UsuarioDaoImp implements IUsuarioDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Usuario> getUsuarios() {
		String query = "FROM Usuario"; 
		return em.createQuery(query).getResultList();
	}

	@Override
	public void eliminar(Long id) {
		Usuario usuario = em.find(Usuario.class, id);
		em.remove(usuario);
	}

	@Override
	public boolean registrar(Usuario usuario) {
		
	
		if(this.verificarUsuario(usuario)) {
			return false;
		}else {
			em.merge(usuario);
			return true;		
		}
		
		
	}
	

	
	@Override
	public Usuario userByCredentials(Usuario usuario) {
		
		String query = "FROM Usuario WHERE email = :email"; 
		List<Usuario> lista =  em.createQuery(query)
				.setParameter("email", usuario.getEmail())
				.getResultList();
		
		if (lista.isEmpty()) {
            return null;
        }
		
		String passwordHashed = lista.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (argon2.verify(passwordHashed, usuario.getPassword())) {
            return lista.get(0);
        }
        return null;
		
	}

	@Override
	public boolean verificarUsuario(Usuario usuario) {
		
		String query = "FROM Usuario WHERE email = :email"; 
		List<Usuario> lista =  em.createQuery(query)
				.setParameter("email", usuario.getEmail())
				.getResultList();
		
		if(lista.isEmpty()) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public Usuario getUsuario(Long id) {
		String query = "FROM Usuario WHERE id =:id";
		List<Usuario> usuario = em.createQuery(query)
				.setParameter("id", id)
				.getResultList();
		
		if(!usuario.isEmpty()) {
			return usuario.get(0);
		}else {			
			return null;
		}
	} 

}
