package com.logistiqal.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NumberVO extends GenericVO {
//	long registrosPorPagina;

	long valor;
	
	public NumberVO(long valor, String mensaje, String codigoError) {
		super(mensaje, codigoError);
		this.valor = valor;
	}
}