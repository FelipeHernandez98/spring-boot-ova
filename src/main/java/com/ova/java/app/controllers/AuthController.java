package com.ova.java.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ova.java.app.dao.IUsuarioDao;
import com.ova.java.app.entitys.Usuario;
import com.ova.java.app.util.JWTUtil;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/auth")
public class AuthController {

	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private JWTUtil jwtUtil;

	@PostMapping(value = "login" )
	public String login(@RequestBody Usuario usuario) {

		Usuario usuarioLogueado = usuarioDao.userByCredentials(usuario);
		
		if(usuarioLogueado != null) {
			
			String token = jwtUtil.create(String.valueOf(usuarioLogueado.getId()), usuarioLogueado.getEmail());
			return token;
			
		}
		return "FAIL";
	}
}
