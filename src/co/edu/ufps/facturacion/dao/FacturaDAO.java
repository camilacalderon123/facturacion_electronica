package co.edu.ufps.facturacion.dao;

import co.edu.ufps.facturacion.connection.Conexion;
import co.edu.ufps.facturacion.entities.*;

public class FacturaDAO extends Conexion<Factura> implements GenericDAO<Factura>{

	public FacturaDAO() {
		super(Factura.class);
	}
}
