package com.logistiqal.demo.vo;

import java.util.List;

import com.logistiqal.demo.modelo.Producto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductoVO extends GenericVO {
	
	List<Producto> productos;

	public ProductoVO(List<Producto> productos, String mensaje, String codigoError) {
		super(mensaje, codigoError);
		this.productos = productos;
	}

}
