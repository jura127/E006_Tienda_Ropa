package com.ipartek.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
	private Integer id;
	private Talla tallaId = new Talla();
	private Integer cantidad; 
}