package com.ipartek.servicios;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.ipartek.modelo.Talla;

@Service
public class TallaServicioImp implements TallaServicio {

	@Value("${url.api.tallas}")
	private String URL;
	private RestClient restClient = RestClient.create();

	@Override
	public ResponseEntity<?> obtenerTodos() {
		return restClient.get().uri(URL).retrieve().toEntity(Talla[].class);
	}
}