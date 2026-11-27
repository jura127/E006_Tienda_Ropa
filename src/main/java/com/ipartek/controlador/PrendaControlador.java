package com.ipartek.controlador;

import java.math.BigDecimal;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ipartek.modelo.Categoria;
import com.ipartek.modelo.Genero;
import com.ipartek.modelo.Prenda;
import com.ipartek.modelo.Talla;
import com.ipartek.servicios.CategoriaServicio;
import com.ipartek.servicios.GeneroServicio;
import com.ipartek.servicios.PrendaServicio;
import com.ipartek.servicios.TallaServicio;

@Controller
public class PrendaControlador {

	private static final Set<String> ORDENES = Set.of("popularidad", "creacion", "precio_asc", "precio_desc");
	
	@Autowired
	private PrendaServicio prendaServ;
	@Autowired
	private GeneroServicio generoServ;
	@Autowired
	private CategoriaServicio categoriaServ;
	@Autowired
	private TallaServicio tallaServ;
	
	private Genero[] generos(ResponseEntity<?> r) {
		Genero[] x = (Genero[]) r.getBody();
		return x == null ? new Genero[0] : x;
	}

	private Categoria[] categorias(ResponseEntity<?> r) {
		Categoria[] x = (Categoria[]) r.getBody();
		return x == null ? new Categoria[0] : x;
	}

	private Talla[] tallas(ResponseEntity<?> r) {
		Talla[] x = (Talla[]) r.getBody();
		return x == null ? new Talla[0] : x;
	}

	private Prenda[] prendas(ResponseEntity<?> r) {
		Prenda[] x = (Prenda[]) r.getBody();
		return x == null ? new Prenda[0] : x;
	}
	
	@GetMapping({ "/", "/prendas" })
	public String prendas(@RequestParam(required = false) Integer genero,
			@RequestParam(required = false) Integer categoria, @RequestParam(required = false) Integer talla,
			@RequestParam(required = false) BigDecimal precioMin, @RequestParam(required = false) BigDecimal precioMax,
			@RequestParam(required = false) String texto, @RequestParam(defaultValue = "popularidad") String orden,
			Model model) {

		if (!ORDENES.contains(orden)) {
			orden = "popularidad";
		}

		Genero[] listaGeneros = new Genero[0];
		Categoria[] listaCategorias = new Categoria[0];
		Talla[] listaTallas = new Talla[0];
		Prenda[] listaPrendas = new Prenda[0];

		try {
			listaGeneros = generos(generoServ.obtenerTodos());
			listaCategorias = categorias(categoriaServ.obtenerTodos());
			listaTallas = tallas(tallaServ.obtenerTodos());

			
			if (genero == null && listaGeneros.length > 0) {
				genero = listaGeneros[0].getId();
			}
			if (genero != null) {
				listaPrendas = prendas(prendaServ.buscar(genero, categoria, talla, precioMin, precioMax, texto, orden));
			}
		} catch (Exception e) {
			model.addAttribute("f_mensaje", "No se ha podido conectar con el servidor. Inténtalo más tarde.");
		}

		String nombreGenero = "";
		for (Genero g : listaGeneros) {
			if (g.getId().equals(genero)) {
				nombreGenero = g.getNombre();
			}
		}

		model.addAttribute("generos", listaGeneros);
		model.addAttribute("categorias", listaCategorias);
		model.addAttribute("tallas", listaTallas);
		model.addAttribute("prendas", listaPrendas);
		model.addAttribute("generoActual", genero);
		model.addAttribute("nombreGenero", nombreGenero);


		model.addAttribute("fCategoria", categoria);
		model.addAttribute("fTalla", talla);
		model.addAttribute("fPrecioMin", precioMin);
		model.addAttribute("fPrecioMax", precioMax);
		model.addAttribute("fTexto", texto);
		model.addAttribute("orden", orden);
		return "prendas";
	}
	
	
}