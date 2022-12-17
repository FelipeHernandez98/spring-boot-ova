package com.ova.java.app.entitys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "calificaciones")
public class Calificacion implements Serializable{
		
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name = "id_usuario", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.EAGER)
	private Usuario idUsuario;
	
	@JoinColumn(name = "id_tema", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.EAGER)
	private Tema idTema;
	
	@Column(name = "calificacion")
	private int calificacion;
	

	public Calificacion() {
	}

	public Calificacion(Long id, Usuario idUsuario, Tema idTema, int calificacion) {
		this.id = id;
		this.idUsuario = idUsuario;
		this.idTema = idTema;
		this.calificacion = calificacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Usuario idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Tema getIdTema() {
		return idTema;
	}

	public void setIdTema(Tema idTema) {
		this.idTema = idTema;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
	
	
}
