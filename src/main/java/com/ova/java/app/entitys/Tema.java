package com.ova.java.app.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "temas")
public class Tema {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name ="numero_tema")
	private int numeroTema;
	
	@Column(name ="nombre_tema")
	private String nombreTema;
	

	public Tema() {

	}

	public Tema(Long id, int numeroTema, String nombreTema) {
		this.id = id;
		this.numeroTema = numeroTema;
		this.nombreTema = nombreTema;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumeroTema() {
		return numeroTema;
	}

	public void setNumeroTema(int numeroTema) {
		this.numeroTema = numeroTema;
	}

	public String getNombreTema() {
		return nombreTema;
	}

	public void setNombreTema(String nombreTema) {
		this.nombreTema = nombreTema;
	}
	
	
	
}
