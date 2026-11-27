package com.ipartek.servicios;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.ipartek.modelo.Prenda;

@Service
public class PrendaServicioImp implements PrendaServicio {

	@Value("${url.api.prendas}")
	private String URL;
	private RestClient restClient = RestClient.create();

	@Override
	public ResponseEntity<?> buscar(Integer genero, Integer categoria, Integer talla, BigDecimal precioMin,
			BigDecimal precioMax, String texto, String orden) {

		URI uri = UriComponentsBuilder.fromUriString(URL + "buscar")
				.queryParamIfPresent("genero", Optional.ofNullable(genero))
				.queryParamIfPresent("categoria", Optional.ofNullable(categoria))
				.queryParamIfPresent("talla", Optional.ofNullable(talla))
				.queryParamIfPresent("precioMin", Optional.ofNullable(precioMin))
				.queryParamIfPresent("precioMax", Optional.ofNullable(precioMax))
				.queryParamIfPresent("texto", Optional.ofNullable(texto).filter(s -> !s.isBlank()))
				.queryParamIfPresent("orden", Optional.ofNullable(orden))
				.build().encode().toUri();

		return restClient.get().uri(uri).retrieve().toEntity(Prenda[].class);
	}
}