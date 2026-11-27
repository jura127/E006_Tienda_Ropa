package com.ipartek.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prenda {
	private Integer id;
	private String nombre;
	private String descripcion;
	private BigDecimal precio;
	private LocalDate fechaSalida;
	
	private Integer visitas;
	private Genero generoId = new Genero();
	private Categoria categoriaId = new Categoria();
	private List<Foto> fotos = new ArrayList<>();
	private List<Stock> stock = new ArrayList<>(); 

	
	public int stockTotal() {
		int total = 0;
		if (stock != null) {
			for (Stock s : stock) {
				if (s.getCantidad() != null && s.getCantidad() > 0) {
					total += s.getCantidad();
				}
			}
		}
		return total;
	}
}