package com.logistiqal.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.logistiqal.demo.modelo.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Integer>, PagingAndSortingRepository<Producto, Integer>  {
	
    @Query("FROM Usuario WHERE codigo = ?1 and nombre = ?2")
    public List<Producto> findByCodigoAndNombre(Integer codigo, String nombre);
    
}