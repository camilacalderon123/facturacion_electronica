package co.edu.ufps.facturacion.dao;

import co.edu.ufps.facturacion.connection.Conexion;
import co.edu.ufps.facturacion.entities.*;

public class DocumentoDAO extends Conexion<Documento> implements GenericDAO<Documento>{

	public DocumentoDAO() {
		super(Documento.class);
	}
}
