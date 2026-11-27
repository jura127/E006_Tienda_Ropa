package com.ipartek.controlador;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class ConfiguracionGlobal {

	@Value("${url.api.fotos}")
	private String urlFotos;

	@ModelAttribute("urlFotos")
	public String urlFotos() {
		return urlFotos;
	}
}