package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Bus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String numero;

	@Column(nullable = false)
	private String placa;
	
	@CreationTimestamp
	@Column(name = "fecha_creacion", nullable = false, updatable = false)
	private LocalDateTime fechaCreacion = LocalDateTime.now();

	@Column(nullable = false)
	private String caracteristicas;

	@ManyToOne
	@JoinColumn(name = "marca_id", nullable = false)
	private Marca marca;

	@Column(nullable = false)
	private boolean activo;

}
