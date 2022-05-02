package co.edu.ufps.facturacion.dao;

import co.edu.ufps.facturacion.connection.Conexion;
import co.edu.ufps.facturacion.entities.DetalleFactura;

public class DetalleFacturaDAO extends Conexion<DetalleFactura> implements GenericDAO<DetalleFactura>{

	public DetalleFacturaDAO() {
		super(DetalleFactura.class);
	}
}
