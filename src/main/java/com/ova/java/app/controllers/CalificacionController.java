package com.ova.java.app.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ova.java.app.entitys.Calificacion;
import com.ova.java.app.entitys.Tema;
import com.ova.java.app.entitys.Usuario;
import com.ova.java.app.repository.ICalificacionRepository;
import com.ova.java.app.repository.ITemaRespository;
import com.ova.java.app.repository.IUsuarioRepository;
import com.ova.java.app.util.JWTUtil;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/cal")
public class CalificacionController {

	@Autowired
	private ICalificacionRepository calificacionRepo;
	
	@Autowired
	private IUsuarioRepository usuarioRepo;
	
	@Autowired
	private ITemaRespository temaRepo;
	
	@Autowired
	private JWTUtil jwtUtil;
	

	@GetMapping(value = "calificacion")
	public List<Calificacion> getCalificaciones(){
		return (List<Calificacion>) calificacionRepo.findAll();
	}
	
	@PostMapping(value = "calificacion")
	public ResponseEntity<String> guardarCalificacion(
			@RequestBody Calificacion calificacion, @RequestHeader(value="Authorization") String token) {
		
		if(this.validarToken(token)) {
			try {
				calificacionRepo.save(calificacion);

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Content-Type", "application/json")
						.body("{\"response\": \"Calificacion registrada\"}");
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Content-Type", "application/json")
						.body("{\"response\": \"No se pudo registrar la calificación\"}");
			}
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Content-Type", "application/json")
					.body("{\"response\": \"No envio token o token no valido\"}");
		}

		
	}
	
	@GetMapping(value="calificacion/{id_usuario}/{id_tema}")
	public Calificacion getCalificacion(
			@PathVariable Long id_usuario, @PathVariable Long id_tema,
			@RequestHeader String token) {
		
		if(this.validarToken(token)) {
			Usuario usuario = usuarioRepo.findById(id_usuario).orElse(null);
			Tema tema = temaRepo.findById(id_tema).orElse(null);
		
		return calificacionRepo.getCalificacionByIds(usuario, tema);
		}else {
			return null;
		}
				
		
	}
	
	private boolean validarToken(String token) {
		String usuarioId = jwtUtil.getKey(token);
		return usuarioId != null;
	}
}
