package com.ipartek.servicios;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.ipartek.modelo.Categoria;

@Service
public class CategoriaServicioImp implements CategoriaServicio {

	@Value("${url.api.categorias}")
	private String URL;
	private RestClient restClient = RestClient.create();

	@Override
	public ResponseEntity<?> obtenerTodos() {
		return restClient.get().uri(URL).retrieve().toEntity(Categoria[].class);
	}
}