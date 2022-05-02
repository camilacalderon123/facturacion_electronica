package co.edu.ufps.facturacion.dao;

import co.edu.ufps.facturacion.connection.Conexion;
import co.edu.ufps.facturacion.entities.*;

public class NotaDAO extends Conexion<Nota> implements GenericDAO<Nota>{

	public NotaDAO() {
		super(Nota.class);
	}
}
