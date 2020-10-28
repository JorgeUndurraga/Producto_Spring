package com.logistiqal.demo.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.logistiqal.demo.LogistiqalApplication;
import com.logistiqal.demo.dao.ProductoRepository;
import com.logistiqal.demo.modelo.Producto;
import com.logistiqal.demo.servicio.ProductoService;
import com.logistiqal.demo.vo.ProductoVO;
import com.logistiqal.demo.vo.NumberVO;

@Service
public class ProductoServiceImpl implements ProductoService{
	
	private static final Logger log = LoggerFactory.getLogger(LogistiqalApplication.class);
	
	@Autowired
	ProductoRepository dao;
	
	com.logistiqal.demo.vo.ProductoVO respuesta;
	
	@Override
	@Transactional(readOnly = true)
	public ProductoVO findByCodigoAndNombre(Integer codigo, String nombre) {
		respuesta = new ProductoVO(new ArrayList<Producto>(), "Ha ocurrido un error", "101" );
		try {
			List<Producto> productos = dao.findByCodigoAndNombre(codigo, nombre);
			
			if(productos.size()>0) {
				respuesta.setProductos(productos);
				respuesta.setMensaje("Producto encontrado correctamente.");
				respuesta.setCodigoError("0");
			
			}else {
				respuesta.setMensaje("Producto no encontrado.");
			}
			
		} catch (Exception e) {
			log.trace("Producto Service: Error en findByCodigoAndNombre", e);
		}
		
		return respuesta;
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public ProductoVO getAllProductos() {
		
		respuesta = new ProductoVO(new ArrayList<Producto>(), "Ha ocurrido un error", "102" );
		
		try {
			respuesta.setProductos((List<Producto>) dao.findAll());
			respuesta.setMensaje(String.format("Se ha/n encontrado %d registro/s", respuesta.getProductos().size()));
			respuesta.setCodigoError("0");
		
		} catch (Exception e) {
			log.trace("Producto Service: Error en getAllProductos", e);
		}
		
		return respuesta;
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public ProductoVO login(Integer codigo, String nombre) {
		respuesta = new ProductoVO(new ArrayList<Producto>(), "Credenciales incorrectas", "103" );
		log.info(String.format("Credenciales: %s %s", codigo, nombre));
		
		if(codigo== 0 || nombre.length()==0) //if(codigo.length()== 0 || nombre.length()==0)

			return respuesta;
		
		respuesta = findByCodigoAndNombre(codigo, nombre);
		
		log.info(String.format("Respuesta Find By codigo And nombre: %s ", respuesta));
		
		if(respuesta.getCodigoError().equals("0")) {
			respuesta.setMensaje(String.format("Bienvenido %s",respuesta.getProductos().get(0).getNombre())); //no es get nombre porque este es un producto
		}
		return respuesta;
	}


	@Override
	@Transactional
	public ProductoVO add(Producto producto) {
		
		respuesta = new ProductoVO(new ArrayList<Producto>(), "Ha ocurrido un error", "104" );
		
		try {
			dao.save(producto);
			respuesta.setMensaje(String.format("Se ha guardado correctamente al producto %s", producto.getNombre())); //no es get codigo tambien
			respuesta.setCodigoError("0");
		
		} catch (Exception e) {
			log.trace("Producto Service: Error en add", e);
		}
		return respuesta;
	}


	@Override
	@Transactional
	public ProductoVO update(Producto producto) {
		respuesta = new ProductoVO(new ArrayList<Producto>(), "Ha ocurrido un error", "105" );
		try {
			dao.save(producto);
			respuesta.setMensaje(String.format("Se ha actualizado correctamente al producto %s", producto.getNombre()));
			respuesta.setCodigoError("0");
		} catch (Exception e) {
			log.trace("Producto Service: Error en update", e);
		}
		return respuesta;
	}

	@Override
	@Transactional
	public ProductoVO delete(Producto producto) {
		respuesta = new ProductoVO(new ArrayList<Producto>(), "Ha ocurrido un error", "106" );
		try {
			dao.delete(producto);
			respuesta.setMensaje("Se ha eliminado correctamente al producto");
			respuesta.setCodigoError("0");
		} catch (Exception e) {
			log.trace("Producto Service: Error en delete", e);
		}
		return respuesta;
	}

	@Override
	@Transactional(readOnly = true)
	public ProductoVO findById(Integer codigo) {
		respuesta = new ProductoVO(new ArrayList<Producto>(), "Ha ocurrido un error", "107" );
		try {
			Producto producto = dao.findById(codigo).get();
			respuesta.getProductos().add(producto);
			respuesta.setMensaje("Producto encontrado correctamente.");
			respuesta.setCodigoError("0");
		} catch (Exception e) {
			log.trace("Producto Service: Error en indById", e);
		}
		return respuesta;
	}
	
	@Override
	@Transactional(readOnly = true)
	public ProductoVO getPage(Integer pagina, Integer cantidad) {
		respuesta = new ProductoVO(new ArrayList<Producto>(), "Ha ocurrido un error", "108" );
		try {
			Pageable pageable = PageRequest.of(pagina,cantidad);
			Page<Producto> responsePage = dao.findAll(pageable);
			respuesta.setProductos(responsePage.getContent());
			respuesta.setMensaje(String.format("Se ha/n encontrado %d registro/s", respuesta.getProductos().size()));
			respuesta.setCodigoError("0");
		} catch (Exception e) {
			log.trace("Producto Service: Error en getPage", e);
		}
		return respuesta;
	}
	

	@Override
	@Transactional(readOnly = true)
	public NumberVO getPageCount(long registrosPorPagina) {
		NumberVO respuesta = new NumberVO(0, "Ha ocurrido un error", "109" );
		try {
			long registros = dao.count();
			if(registrosPorPagina == 0 && registros == 0) {
				respuesta.setValor(1);
			}else {
				respuesta.setValor((registros/registrosPorPagina) + (registros % registrosPorPagina == 0 ? 0 : 1));
			}
			respuesta.setCodigoError("201");
			respuesta.setMensaje(String.format("Hay %d paginas", respuesta.getValor()));
		} catch (Exception e) {
			log.trace("Usuario Service: Error en getPageCount", e);
		}
		return respuesta;
	}
}
