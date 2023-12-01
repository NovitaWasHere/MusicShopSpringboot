package com.nebrija.mvc.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Instrumento")
public class Instrumento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Id",nullable=false, unique=true)
	private long id;
	
	@Column(name = "Nombre",nullable=false)
	private String nombre;
	
	@Column(name = "Precio",nullable=false)
	private int precio;
	
	@Column(name = "Descripcion",nullable=false)
	private String descripcion;
	
	@Column(name = "dImagen",nullable=false)
	private String dImagen;
	
	//Al parecer hay algun tipo de error y no sirven las etiquetas que se suponen hacen esto solo por lo que igual lo pondre
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getdImagen() {
		return dImagen;
	}

	public void setdImagen(String dImagen) {
		this.dImagen = dImagen;
	}

	@Override
	public String toString() {
		return "Instrumento [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", descripcion=" + descripcion
				+ "]";
	}

	

}
