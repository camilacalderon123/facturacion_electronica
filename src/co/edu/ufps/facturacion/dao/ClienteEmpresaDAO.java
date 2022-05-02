package co.edu.ufps.facturacion.dao;

import co.edu.ufps.facturacion.connection.Conexion;
import co.edu.ufps.facturacion.entities.ClienteEmpresa;

public class ClienteEmpresaDAO extends Conexion<ClienteEmpresa> implements GenericDAO<ClienteEmpresa> {

	public ClienteEmpresaDAO() {
		super(ClienteEmpresa.class);
	}

}
