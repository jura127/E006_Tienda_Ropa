package com.ipartek.servicios;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;

public interface PrendaServicio {
	public ResponseEntity<?> buscar(Integer genero, Integer categoria, Integer talla, BigDecimal precioMin,
			BigDecimal precioMax, String texto, String orden);
}