package com.ipartek.servicios;

import org.springframework.http.ResponseEntity;

public interface GeneroServicio {
	public ResponseEntity<?> obtenerTodos();
}