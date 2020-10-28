package com.logistiqal.demo.servicio;

import com.logistiqal.demo.modelo.Producto;
import com.logistiqal.demo.vo.NumberVO;
import com.logistiqal.demo.vo.ProductoVO;

public interface ProductoService {
	public ProductoVO getAllProductos();
	public ProductoVO findByCodigoAndNombre(Integer codigo, String nombre);
	public ProductoVO login(Integer codigo, String nombre);
	public ProductoVO add(Producto producto);
	public ProductoVO update(Producto producto);
	public ProductoVO delete(Producto producto);
	public ProductoVO findById(Integer codigo);
	public ProductoVO getPage(Integer pagina, Integer cantidad);
	NumberVO getPageCount(long registrosPorPagina);

}
