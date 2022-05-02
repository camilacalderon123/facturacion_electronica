package co.edu.ufps.facturacion.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import co.edu.ufps.facturacion.connection.Conexion;
import co.edu.ufps.facturacion.entities.*;

public class ProductoDAO extends Conexion<Producto> implements GenericDAO<Producto>{

	public ProductoDAO() {
		super(Producto.class);
	}
	
	public List<Producto> listarPorEmpresa(int nit){
		Query consulta= getEm().createNamedQuery(Producto.class.getSimpleName()+".findByEmpresa", Producto.class).setParameter("nit", nit);
		List<Producto> lista = (ArrayList<Producto>) consulta.getResultList();
		return lista;
	}
}
