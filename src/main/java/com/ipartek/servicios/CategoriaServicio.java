package com.ipartek.servicios;

import org.springframework.http.ResponseEntity;

public interface CategoriaServicio {
	public ResponseEntity<?> obtenerTodos();
}