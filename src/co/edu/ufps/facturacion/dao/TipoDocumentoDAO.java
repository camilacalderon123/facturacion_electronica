package co.edu.ufps.facturacion.dao;

import co.edu.ufps.facturacion.connection.Conexion;
import co.edu.ufps.facturacion.entities.*;

public class TipoDocumentoDAO extends Conexion<TipoDocumento> implements GenericDAO<TipoDocumento>{

	public TipoDocumentoDAO() {
		super(TipoDocumento.class);
	}
}
