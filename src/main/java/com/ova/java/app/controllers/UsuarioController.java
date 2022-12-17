package com.ova.java.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ova.java.app.dao.IUsuarioDao;
import com.ova.java.app.entitys.Usuario;
import com.ova.java.app.util.JWTUtil;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@RestController
@RequestMapping("/api")
public class UsuarioController {
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private JWTUtil jwtUtil;

	@GetMapping(value = "usuario/{id}")
	public Usuario getUsuario(@PathVariable Long id) {
		return usuarioDao.getUsuario(id);
	}
	
	@GetMapping(value = "usuarios")
	public List<Usuario> getUsuarios(@RequestHeader(value="Authorization") String token) {
		if (!this.validarToken(token))  return null; 
		return usuarioDao.getUsuarios();
	}
	
	private boolean validarToken(String token) {
		String usuarioId = jwtUtil.getKey(token);
		return usuarioId != null;
	}
	
//	@PatchMapping
//	public Usuario editarUsuario() {
//		
//		
//	}
//	
	@DeleteMapping(value = "usuarios/{id}")
	public void eliminarUsuario(@PathVariable Long id,
			@RequestHeader(value="Authorization") String token) {
		if (!this.validarToken(token))  return; 
		usuarioDao.eliminar(id);		
	}
//	
	
	@PostMapping(value = "usuarios")
	public ResponseEntity<String> registrarUsuario(
			@RequestBody Usuario usuario, @RequestHeader String token) {
		
		if(this.validarToken(token)) {
			
			Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
			String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
			usuario.setPassword(hash);
			
			if(!usuarioDao.registrar(usuario)) {
				return ResponseEntity
						.status(HttpStatus.BAD_REQUEST)
						.header("Content-Type","application/json")
						.body("{\"response\": \"El email ya esta registrado\"}");
			}else {
				return ResponseEntity
						.status(HttpStatus.OK)
						.header("Content-Type","application/json")
						.body("{\"response\": \"Registrado correctamente\"}");
			}

		} else {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.header("Content-Type","application/json")
					.body("{\"response\": \"No envio token o el token no es valido\"}");
		}

    }
	

	
}
