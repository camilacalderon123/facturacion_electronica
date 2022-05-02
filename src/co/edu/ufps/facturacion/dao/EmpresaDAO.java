package co.edu.ufps.facturacion.dao;

import co.edu.ufps.facturacion.connection.Conexion;
import co.edu.ufps.facturacion.entities.*;

public class EmpresaDAO extends Conexion<Empresa> implements GenericDAO<Empresa>{

	public EmpresaDAO() {
		super(Empresa.class);
	}
}
