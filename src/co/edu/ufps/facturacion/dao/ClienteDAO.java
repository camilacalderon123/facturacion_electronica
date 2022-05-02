package co.edu.ufps.facturacion.dao;

import java.util.List;

import co.edu.ufps.facturacion.connection.Conexion;
import co.edu.ufps.facturacion.entities.Cliente;

public class ClienteDAO extends Conexion<Cliente> implements GenericDAO<Cliente> {

	public ClienteDAO() {
		super(Cliente.class);
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> findByEmpresa(int nit) {

		return getEm().createQuery(
				"SELECT c FROM cliente c INNER JOIN cliente_empresa cl on cl.cliente.numeroDocumento=c.numeroDocumento WHERE cl.empresa.nit=:nit and c.estado=1")
				.setParameter("nit", nit).getResultList();
	}
}
