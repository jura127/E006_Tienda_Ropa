package com.ipartek.servicios;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.ipartek.modelo.Genero;

@Service
public class GeneroServicioImp implements GeneroServicio {

	@Value("${url.api.generos}")
	private String URL;
	private RestClient restClient = RestClient.create();

	@Override
	public ResponseEntity<?> obtenerTodos() {
		return restClient.get().uri(URL).retrieve().toEntity(Genero[].class);
	}
}